package com.realtech.pandora.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.realtech.pandora.common.exception.ProjectException;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.dao.PersonAlertLinkMapper;
import com.realtech.pandora.dao.PersonMapper;
import com.realtech.pandora.dao.ProofAlertSettingMapper;
import com.realtech.pandora.dao.ProofDeviceMapper;
import com.realtech.pandora.domain.Person;
import com.realtech.pandora.domain.PersonAlertLink;
import com.realtech.pandora.domain.ProofAlertSetting;
import com.realtech.pandora.domain.DTO.ProofAlertSettingDTO;
import com.realtech.pandora.service.ProofAlertSettingService;
import com.realtech.pandora.util.DateUtil;
import com.realtech.pandora.util.OidUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProofAlertSettingServiceBean implements ProofAlertSettingService {

	@Resource
	private ProofAlertSettingMapper proofAlertSettingMapper;
	@Resource
	private ProofDeviceMapper proofDeviceMapper;
	@Resource
	private PersonAlertLinkMapper personAlertLinkMapper;
	@Resource
	private PersonMapper personMapper;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus addAlertSettings(List<ProofAlertSettingDTO> proofAlertSettings) {
		String proofEquipId = proofAlertSettings.get(0).getProofDeviceId();
		// 去数据库查询 防错装置id是否存在
		if (proofDeviceMapper.selectByPrimaryKey(proofEquipId) == null) {
			log.info("不存在此防错装置id......id：{}", proofEquipId);
			return ResultStatus.DATA_NOT_FIND;
		}
		// 添加的层数必须要小于3
		ProofAlertSetting countProofAlertSetting = new ProofAlertSetting();
		countProofAlertSetting.setProofDeviceId(proofEquipId);
		int count = proofAlertSettingMapper.selectCount(countProofAlertSetting);
		if (count + proofAlertSettings.size() > 3) {
			return ResultStatus.ALERT_CAN_NOT_GT_THREE;
		}
		for (ProofAlertSettingDTO item : proofAlertSettings) {
			if (item.getProofDeviceId() == null) { // 抛出异常 回滚
				throw new ProjectException(ResultStatus.LACK_PARAM, "设备装置id不能为空");
			}
			if (!item.getProofDeviceId().equals(proofEquipId)) {
				throw new ProjectException(ResultStatus.DATA_NOT_FIND, "设备装置未找到");
			}
			ProofAlertSetting proofAlertSetting = new ProofAlertSetting();
			proofAlertSetting.setCreateAt(DateUtil.format(new Date()));
			proofAlertSetting.setCreateBy("创建人");
			proofAlertSetting.setDeleteFlag(0);
			proofAlertSetting.setDataVersion(1);
			proofAlertSetting.setFactoryId("factroyId");
			proofAlertSetting.setNotifyTimeLimitUom(item.getNotifyTimeLimitUom());
			proofAlertSetting.setProofDeviceId(item.getProofDeviceId());
			proofAlertSetting.setType(item.getType());
			String alertId = OidUtil.newId();
			proofAlertSetting.setId(alertId);
			BigDecimal bigDecimal = new BigDecimal(item.getNotifyTimeLimit());
			proofAlertSetting.setNotifyTimeLimit(bigDecimal);
			proofAlertSettingMapper.insertSelective(proofAlertSetting);
			// 批量插入人员-报警装置关联表,可优化改为批量插入
			for (String personId : item.getPersons()) {
				PersonAlertLink personAlertLink = new PersonAlertLink();
				personAlertLink.setId(OidUtil.newId());
				personAlertLink.setAlertId(alertId); // 报警装置id
				personAlertLink.setPersonId(personId);// 人员id
				personAlertLink.setCreateAt(DateUtil.format(new Date()));
				personAlertLink.setCreateBy("创建人");
				personAlertLink.setDeleteFlag(0);
				personAlertLink.setDataVersion(1);
				personAlertLinkMapper.insertSelective(personAlertLink);
			}

		}
		return ResultStatus.SUCCESS;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<ProofAlertSettingDTO> findListByProofId(String id) {
		ProofAlertSetting findAlert = new ProofAlertSetting();
		findAlert.setProofDeviceId(id);
		List<ProofAlertSetting> proofAlertSettings = proofAlertSettingMapper.select(findAlert);
		List<ProofAlertSettingDTO> proofAlertSettingDTOs = new ArrayList<ProofAlertSettingDTO>();
		for (ProofAlertSetting proofAlertSetting : proofAlertSettings) {
			ProofAlertSettingDTO proofAlertSettingDTO = new ProofAlertSettingDTO();
			List<Person> persons = personMapper.findPersonByAlertId(proofAlertSetting.getId());
			proofAlertSettingDTO.setPersonList(persons);
			proofAlertSettingDTO.setType(proofAlertSetting.getType());
			proofAlertSettingDTO.setNotifyTimeLimit(proofAlertSetting.getNotifyTimeLimit().longValue());
			proofAlertSettingDTO.setNotifyTimeLimitUom(proofAlertSetting.getNotifyTimeLimitUom());
			proofAlertSettingDTO.setId(proofAlertSetting.getId());
			proofAlertSettingDTOs.add(proofAlertSettingDTO);
		}
		return proofAlertSettingDTOs;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus editAlertSettings(List<ProofAlertSettingDTO> proofAlertSettings) {
		// 需要事务控制，所有在业务层中 聚合删除和新增两个操作
		String proofEquipId = proofAlertSettings.get(0).getProofDeviceId();
		for (ProofAlertSettingDTO item : proofAlertSettings) {
			if (item.getProofDeviceId() == null) { // 抛出异常 回滚
				throw new ProjectException(ResultStatus.LACK_PARAM, "设备装置id不能为空");
			}
			if (!item.getProofDeviceId().equals(proofEquipId)) {
				throw new ProjectException(ResultStatus.DATA_NOT_FIND, "设备装置未找到");
			}
		}
		// 第一步,删除原先存在的报警装置，以及报警责任人
		ResultStatus delResult = delAlertSettings(proofEquipId);
		if (delResult.getCode() != 0) {
			return delResult;
		}
		// 第二步，调用保存逻辑，一旦失败 回滚事务
		ResultStatus addResult = addAlertSettings(proofAlertSettings);
		if (addResult.getCode() != 0) {
			throw new ProjectException(addResult);// 抛出异常 事务 回滚 A,B嵌套事务
		}
		return ResultStatus.SUCCESS;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus delAlertSettings(String proofDeviceId) {
		// 删除人员-报警关联
		personAlertLinkMapper.deleteByProofDeviceId(proofDeviceId);
		// 删除警报装置
		ProofAlertSetting deleteProofAlertSetting = new ProofAlertSetting();
		deleteProofAlertSetting.setProofDeviceId(proofDeviceId);
		proofAlertSettingMapper.delete(deleteProofAlertSetting);
		return ResultStatus.SUCCESS;
	}

}
