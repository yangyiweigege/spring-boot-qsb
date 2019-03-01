package com.realtech.pandora.service.impl;
import com.github.pagehelper.PageHelper;
import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.LoginUserInfo;
import com.realtech.pandora.dao.*;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.*;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.ProofDeviceService;
import com.realtech.pandora.util.DateUtil;
import com.realtech.pandora.util.OidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProofDeviceServiceBean implements ProofDeviceService {

	@Resource
	private ProofDeviceMapper proofDeviceMapper;
	@Resource
	private PersonMapper personMapper;
	@Resource
	private EquipmentMapper equipmentMapper;
	@Resource
	private ProofDevicePersonLinkMapper proofDevicePersonLinkMapper;
	@Resource
	private MeasureTemplateMapper measureTemplateMapper;
	@Resource
	private FactoryLocationMapper factoryLocationMapper;
	@Resource
	private MeasureRecordMapper measureRecordMapper;
	@Resource
	private ProofAlertSettingMapper proofAlertSettingMapper;
	@Resource
	private PersonAlertLinkMapper personAlertLinkMapper;
	@Resource
	private SystemResourceMapper systemResourceMapper;

	/**
	 * 根据条件查询防错记录
	 * 
	 * @param proofDeviceDTO
	 * @param page
	 * @return
	 */
	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<ProofDeviceDTO> queryErrorRecord(ProofDeviceDTO proofDeviceDTO, PageBean page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		if (proofDeviceDTO.getId() == null) {
			if (proofDeviceDTO.getProofType() == VerificationEnum.ErrorProofType_ALL.getCode()) {
				proofDeviceDTO.setProofType(null);
			}
			if (proofDeviceDTO.getIsItem() == VerificationEnum.IfCheckItem_ALL.getCode()) {
				proofDeviceDTO.setIsItem(null);
			}
			if (proofDeviceDTO.getStatus() == VerificationEnum.TempStates_ALL.getCode()) {
				proofDeviceDTO.setStatus(null);
			}
		}
		List<ProofDeviceDTO> proofDeviceDTOList = proofDeviceMapper.queryErrorRecord(proofDeviceDTO);

		// 设置地点，运行状态（导出时用），验证频率（导出时用）
		/*Example example = new Example(FactoryLocation.class);
		for (ProofDeviceDTO proofDeviceDTO1 : proofDeviceDTOList) {
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
			criteria.andEqualTo("id", proofDeviceDTO1.getFactoryLocationId());
			List<FactoryLocation> factoryLocationList = factoryLocationMapper.selectByExample(example);
			if (proofDeviceDTO1.getStatus() != null) {
				if (proofDeviceDTO1.getStatus() == 0) {
					proofDeviceDTO1.setStatusStr("运行中");
				} else {
					proofDeviceDTO1.setStatusStr("已失效");
				}
			}
			if (proofDeviceDTO1.getVerificationTime() != null && proofDeviceDTO1.getVerificationTimeNum() != null
					&& proofDeviceDTO1.getVerificationTimeUnit() != null) {
				String verificationTimeUnit = null;
				switch (proofDeviceDTO1.getVerificationTimeUnit()) {
				case 0:
					verificationTimeUnit = "天";
					break;
				case 1:
					verificationTimeUnit = "周";
					break;
				case 2:
					verificationTimeUnit = "月";
					break;
				case 3:
					verificationTimeUnit = "时";
					break;
				case 4:
					verificationTimeUnit = "分";
					break;
				default:
					verificationTimeUnit = "秒";
					break;
				}
				String verificationNumStr = proofDeviceDTO1.getVerificationTime() + verificationTimeUnit
						+ proofDeviceDTO1.getVerificationTimeNum() + "次";
				proofDeviceDTO1.setVerificationNumStr(verificationNumStr);
			}
			// 查询图片数据
			SystemResource systemResource = systemResourceMapper.findResoureByObjId(proofDeviceDTO1.getId());
			if (systemResource != null) {
				proofDeviceDTO1.setImageName(systemResource.getName());
			}
			proofDeviceDTO1.setLocation(factoryLocationList.get(0).getName());
		}*/
		return proofDeviceDTOList;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus addProofDevice(ProofDevice proofDevice, List<MeasureTemplate> measureTemplates,
			List<String> persons, LoginUserInfo loginUserInfo) {
		// 判定用户上传的责任人是否合法
		List<Person> personList = personMapper.findSubmitPerson(persons);
		if (personList.size() != persons.size()) { // 不合法
			log.info("上传的用户id中...存在非法数据(数据库中无法找到该id) id：{}", persons.toString());
			return ResultStatus.PERSON_IS_ILLEGAL;
		}
		// 拼接所有责任人，以逗号形式分割
		StringBuilder allPerson = new StringBuilder();
		for (Person item : personList) {
			allPerson.append(item.getName() + ",");
		}
		// 根据上传的工厂虚拟设备id,查询出真实的设备id
		Equipment equipSearch = new Equipment();
		equipSearch.setDeleteFlag(0);// 未被删除
		equipSearch.setFactoryLocationId(proofDevice.getFactoryId());
		equipSearch = equipmentMapper.selectOne(equipSearch);
		if (equipSearch == null) { // 未查找到虚拟设备对应的真实设备
			log.info("虚拟设备对应的真实设备并未找到...设备虚拟id：{}", proofDevice.getFactoryId());
			return ResultStatus.VIRTUAL_DEVICE_NOT_MATCH;
		}
		// 插入防错装置表
		proofDevice.setEquipmentId(equipSearch.getId());
		proofDevice.setCreateAt(DateUtil.format(new Date()));// 设置创建时间
		proofDevice.setCreateBy(loginUserInfo.getUserName());
		proofDevice.setFactoryId(loginUserInfo.getFactoryId());//工厂id
		proofDevice.setDeleteFlag(0);
		String proofId = OidUtil.newId();
		proofDevice.setId(proofId);
		proofDevice.setDataVersion(1);
		proofDeviceMapper.insertSelective(proofDevice);// 插入防错装置表

		// 写入防错装置_责任人关联表
		List<ProofDevicePersonLink> links = new ArrayList<ProofDevicePersonLink>();
		for (String person : persons) { // 防错装置_责任人关联表
			ProofDevicePersonLink personLink = new ProofDevicePersonLink();
			String personLinkId = OidUtil.newId();
			personLink.setProofDeviceId(proofId); // 防错装置id
			personLink.setId(personLinkId);// id
			personLink.setPersonId(person);// 人员id
			personLink.setCreateAt(DateUtil.format(new Date()));
			personLink.setDeleteFlag(0);
			personLink.setCreateBy(loginUserInfo.getUserName());
			personLink.setPersons(allPerson.substring(0, allPerson.length() - 1));
			links.add(personLink);
		}
		proofDevicePersonLinkMapper.insertBatch(links);

		// 写入防错装置模板表
		for (MeasureTemplate measureTemplate : measureTemplates) { // 防错措施模板
			measureTemplate.setProofDeviceId(proofId);
			measureTemplate.setCreateAt(DateUtil.format(new Date()));
			measureTemplate.setCreateBy(loginUserInfo.getUserName());
			measureTemplate.setDeleteFlag(0);
			measureTemplate.setDataVersion(1);
			measureTemplate.setId(OidUtil.newId());
			measureTemplateMapper.insert(measureTemplate);
		}
		// measureTemplateMapper.insertBatch(measureTemplates); 改进再用

		return ResultStatus.SUCCESS;
	}

	/**
	 * 根据名字模糊查询人员表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<Person> queryPersonList(String name, PageBean page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Example example = new Example(Person.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		criteria.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
		List<Person> personList = personMapper.selectByExample(example);
		return personList;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus editProofDevice(ProofDevice proofDevice, List<MeasureTemplate> measureTemplates,
			List<String> persons, LoginUserInfo loginUserInfo) {
		// 判定用户上传的责任人是否合法
		List<Person> personList = personMapper.findSubmitPerson(persons);
		if (personList.size() != persons.size()) { // 不合法
			log.info("上传的用户id中...存在非法数据(数据库中无法找到该id) id：{}" + persons.toString());
			return ResultStatus.PERSON_IS_ILLEGAL;
		}
		// 拼接所有责任人，以逗号形式分割
		StringBuilder allPerson = new StringBuilder();
		for (Person item : personList) {
			allPerson.append(item.getName() + ",");
		}
		// 根据上传的工厂虚拟设备id,查询出真实的设备id
		Equipment equipSearch = new Equipment();
		equipSearch.setDeleteFlag(0);// 未被删除
		equipSearch.setFactoryLocationId(proofDevice.getFactoryId());
		equipSearch = equipmentMapper.selectOne(equipSearch);
		if (equipSearch == null) { // 未查找到虚拟设备对应的真实设备
			log.info("虚拟设备对应的真实设备并未找到...设备虚拟id：{}", proofDevice.getFactoryId());
			return ResultStatus.VIRTUAL_DEVICE_NOT_MATCH;
		}
		// 修改防错装置表
		proofDevice.setEquipmentId(equipSearch.getId());//设置绑定设备
		proofDevice.setUpdateAt(DateUtil.format(new Date()));// 设置更新时间
		proofDevice.setUpdateBy(loginUserInfo.getUserName());
		proofDevice.setFactoryId(loginUserInfo.getFactoryId());
		proofDeviceMapper.updateByPrimaryKeySelective(proofDevice);// 修改防错装置表

		// 删除原来的责任关联人
		ProofDevicePersonLink deletePersonLink = new ProofDevicePersonLink();
		deletePersonLink.setProofDeviceId(proofDevice.getId());
		proofDevicePersonLinkMapper.delete(deletePersonLink);
		// 写入防错装置_责任人关联表
		List<ProofDevicePersonLink> links = new ArrayList<ProofDevicePersonLink>();
		for (String person : persons) { // 防错装置_责任人关联表
			ProofDevicePersonLink personLink = new ProofDevicePersonLink();
			String personLinkId = OidUtil.newId();
			personLink.setProofDeviceId(proofDevice.getId()); // 防错装置id
			personLink.setId(personLinkId);// id
			personLink.setPersonId(person);// 人员id
			personLink.setCreateAt(DateUtil.format(new Date()));
			personLink.setDeleteFlag(0);
			personLink.setCreateBy(loginUserInfo.getUserName());
			personLink.setPersons(allPerson.substring(0, allPerson.length() - 1));
			links.add(personLink);
		}
		proofDevicePersonLinkMapper.insertBatch(links);
		// 删除原来的防错措施模板
		MeasureTemplate delteTemplate = new MeasureTemplate();
		delteTemplate.setProofDeviceId(proofDevice.getId());
		measureTemplateMapper.delete(delteTemplate);
		// 写入防错装置模板表
		for (MeasureTemplate measureTemplate : measureTemplates) { // 防错措施模板
			measureTemplate.setProofDeviceId(proofDevice.getId());
			measureTemplate.setCreateAt(DateUtil.format(new Date()));
			measureTemplate.setCreateBy(loginUserInfo.getUserName());
			measureTemplate.setDeleteFlag(0);
			measureTemplate.setDataVersion(1);
			measureTemplate.setId(OidUtil.newId());
			measureTemplateMapper.insert(measureTemplate);
		}

		// measureTemplateMapper.insertBatch(measureTemplates); 改进再用

		return ResultStatus.SUCCESS;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus deleteProofDeviceById(String id) {
		// 查询改防错装置下，是否存在防错记录，如果有不给删除
		MeasureRecord findMeasureRecord = new MeasureRecord();
		findMeasureRecord.setProofDeviceId(id);
		findMeasureRecord.setDeleteFlag(0);
		List<MeasureRecord> measureRecords = measureRecordMapper.select(findMeasureRecord);
		if (measureRecords.size() > 0) {
			log.info("要删除的防错装置存在防错记录....装置id:{}", id);
			return ResultStatus.PROOF_DEVICE_EXIST_MEASURE_RECORD;
		}
		// 删除防错装置
		if (proofDeviceMapper.deleteByPrimaryKey(id) < 1) {
			log.info("上传的装置id,在数据库中并不存在....id:{}", id);
			return ResultStatus.DELETE_FAIL;
		}
		// 删除防错装置模板
		MeasureTemplate delteTemplate = new MeasureTemplate();
		delteTemplate.setProofDeviceId(id);
		measureTemplateMapper.delete(delteTemplate);
		// 删除原来的责任关联人
		ProofDevicePersonLink deletePersonLink = new ProofDevicePersonLink();
		deletePersonLink.setProofDeviceId(id);
		proofDevicePersonLinkMapper.delete(deletePersonLink);
		// 删除报警-人员关联表
		personAlertLinkMapper.deleteByProofDeviceId(id);
		// 删除警报装置
		ProofAlertSetting deleteProofAlertSetting = new ProofAlertSetting();
		deleteProofAlertSetting.setProofDeviceId(id);
		proofAlertSettingMapper.delete(deleteProofAlertSetting);
		return ResultStatus.SUCCESS;
	}

	/**
	 * 导出防错清单数据
	 * 
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ProofDeviceDTO> exportProofList(String factoryId) {
		if (factoryId.equals(VerificationEnum.FACTORY_ID.getMsg())) {
			factoryId = null;
		}
		List<ProofDeviceDTO> list = proofDeviceMapper.exportProofList(factoryId);
		if (!CollectionUtils.isEmpty(list)) {
			for (ProofDeviceDTO proofDeviceDTO : list) {
				String verificationTimeUnit = null;
				switch (proofDeviceDTO.getVerificationTimeUnit()) {
				case 0:
					verificationTimeUnit = "天";
					break;
				case 1:
					verificationTimeUnit = "周";
					break;
				case 2:
					verificationTimeUnit = "月";
					break;
				case 3:
					verificationTimeUnit = "时";
					break;
				case 4:
					verificationTimeUnit = "分";
					break;
				default:
					verificationTimeUnit = "秒";
					break;
				}
				String verificationNumStr = proofDeviceDTO.getVerificationTime() + verificationTimeUnit
						+ proofDeviceDTO.getVerificationTimeNum() + "次";
				proofDeviceDTO.setVerificationNumStr(verificationNumStr);// 设置验证频率
				String maintenanceTimeUnit = null;
				switch (proofDeviceDTO.getMaintenanceTimeUnit()) {
				case 0:
					maintenanceTimeUnit = "天";
					break;
				case 1:
					maintenanceTimeUnit = "周";
					break;
				default:
					maintenanceTimeUnit = "月";
					break;
				}
				String maintenanceNumStr = proofDeviceDTO.getMaintenanceTime() + maintenanceTimeUnit
						+ proofDeviceDTO.getMaintenanceTimeNum() + "次";
				proofDeviceDTO.setMaintenanceNumStr(maintenanceNumStr);// 设置保养频率

				// 车间
				FactoryLocation factoryLocation = factoryLocationMapper
						.queryWorkShopById(proofDeviceDTO.getFactoryLocationId());
				if (factoryLocation != null) {
					proofDeviceDTO.setWorkshop(factoryLocation.getName());
				}

				// 防错类型
				if (proofDeviceDTO.getProofType() != null) {
					if (VerificationEnum.ErrorProofType_DEFEND.getCode() == proofDeviceDTO.getProofType()) {
						proofDeviceDTO.setProofTypeStr(VerificationEnum.ErrorProofType_DEFEND.getMsg());
					} else {
						proofDeviceDTO.setProofTypeStr(VerificationEnum.ErrorProofType_SEARCH.getMsg());
					}
				}

				// 是否点检项
				if (proofDeviceDTO.getIsItem() != null) {
					if (VerificationEnum.IfCheckItem_NO.getCode() == proofDeviceDTO.getIsItem()) {
						proofDeviceDTO.setIsItemStr(VerificationEnum.IfCheckItem_NO.getMsg());
					} else {
						proofDeviceDTO.setIsItemStr(VerificationEnum.IfCheckItem_YES.getMsg());
					}
				}

				// 验证方法
				if (proofDeviceDTO.getVerificationMethod() != null) {
					if (VerificationEnum.IdentifyMethods_ARTIFICIAL.getCode() == proofDeviceDTO
							.getVerificationMethod()) {
						proofDeviceDTO.setVerificationMethodStr(VerificationEnum.IdentifyMethods_ARTIFICIAL.getMsg());
					} else {
						proofDeviceDTO.setVerificationMethodStr(VerificationEnum.IdentifyMethods_AUTO.getMsg());
					}
				}
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageBean<ProofDeviceDTO> findBindProofDevice(String id, PageBean<ProofDeviceDTO> pageBean) {
		int count = proofDeviceMapper.countProofDeviceByPid(id);
		if (count == 0) { // 为0 直接返回 不查询
			pageBean.setTotal(count);
			pageBean.setList(new ArrayList<ProofDeviceDTO>());
			return pageBean;
		}
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize(), false);
		List<ProofDevice> proofDevices = proofDeviceMapper.findProofDeviceByPid(id);
		List<ProofDeviceDTO> proofDeviceDTOs = new ArrayList<ProofDeviceDTO>();
		for (ProofDevice device : proofDevices) {
			ProofDeviceDTO item = new ProofDeviceDTO();
			item.setCode(device.getCode());
			item.setName(device.getName());
			item.setStatus(device.getStatus());
			item.setId(device.getId());
			proofDeviceDTOs.add(item);
		}
		pageBean.setTotal(count);
		pageBean.setList(proofDeviceDTOs);
		return pageBean;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProofDevice> findProofDeviceByCode(String code) {
		List<ProofDevice> list = proofDeviceMapper.findProofDeviceByCode(code);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProofDeviceDTO> findProofDeviceById(String id) {
		List<ProofDeviceDTO> list = proofDeviceMapper.findProofDeviceById(id);
		return list;
	}
}


