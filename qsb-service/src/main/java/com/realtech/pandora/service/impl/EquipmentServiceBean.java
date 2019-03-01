package com.realtech.pandora.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.exception.ProjectException;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.dao.EquipmentMapper;
import com.realtech.pandora.dao.FactoryLocationMapper;
import com.realtech.pandora.dao.ProofDeviceMapper;
import com.realtech.pandora.dao.ProofMapMapper;
import com.realtech.pandora.domain.Equipment;
import com.realtech.pandora.domain.ProofDevice;
import com.realtech.pandora.domain.ProofMap;
import com.realtech.pandora.domain.DTO.EquipmentDTO;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.EquipmentService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional
public class EquipmentServiceBean implements EquipmentService {

	@Resource
	private FactoryLocationMapper factoryLocationMapper;
	@Resource
	private EquipmentMapper equipmentMapper;
	@Resource
	private ProofDeviceMapper proofDeviceMapper;
	@Resource
	private ProofMapMapper proofMapMapper;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public PageBean<EquipmentDTO> getDeviceAndProof(String id, PageBean<EquipmentDTO> pageBean) {
		/*
		 * int totalCount =
		 * factoryLocationMapper.findBindDeviceByPidCount(id);// 查询记录总数 if
		 * (totalCount == 0) { pageBean.setTotal(0); pageBean.setList(new
		 * ArrayList<EquipmentDTO>()); return pageBean; } // 第一步，先查询虚拟设备(分页)
		 * PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize(),
		 * false); List<FactoryLocation> factoryLocations =
		 * factoryLocationMapper.findBindDeviceByPid(id); if
		 * (factoryLocations.size() > 0 &&
		 * factoryLocations.get(0).getType().intValue() != 5) { // 地点不是倒数第二层
		 * log.info("上传的地点id下一层并非设备...地点id:{}", id); throw new
		 * ProjectException(ResultStatus.FACTORY_TYPE_NOT_MATCH); } List<String>
		 * factoryLocationIds = new ArrayList<String>(); for (FactoryLocation
		 * factoryLocation : factoryLocations) {
		 * factoryLocationIds.add(factoryLocation.getId());// 设备外键关联 }
		 */
		ProofMap countProofMap = new ProofMap();
		countProofMap.setPid(id);
		int totalCount = proofMapMapper.selectCount(countProofMap);
		if (totalCount == 0) {
			pageBean.setList(new ArrayList<EquipmentDTO>(0));
			pageBean.setTotal(0);
			return pageBean;
		}
		// 第二步，查询地段对应的设备
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize(),false);
		List<Equipment> equipments = equipmentMapper.findEquipmentByPid(id);
		List<String> equipmentIds = new ArrayList<String>();
		for (Equipment item : equipments) {
			equipmentIds.add(item.getId());
		}
		if (equipmentIds.isEmpty()) {
			throw new ProjectException(ResultStatus.DATA_NOT_FIND, "查询超出范围了!");
		}
		// 第三步，对真实设备绑定的防错设备节点进行分组统计
		List<Map<String, Object>> groupMap = proofDeviceMapper.groupByEquipmentIds(equipmentIds);
		// 最终拼接数据，得到结果(设备对应的防错节点异常个数)
		List<EquipmentDTO> equipmentDTOs = new ArrayList<EquipmentDTO>();
		for (Equipment item : equipments) {
			EquipmentDTO equipmentDTO = new EquipmentDTO();
			equipmentDTO.setName(item.getName());
			equipmentDTO.setId(item.getId());
			equipmentDTO.setModelId(item.getModelId());
			equipmentDTO.setExceptionCount(0);
			equipmentDTO.setCurrentStatus(VerificationEnum.TempStates_ONGOING.getMsg());
			for (Map<String, Object> per : groupMap) {
				// 如果相等，则说明该设备有异常防错节点
				if (per.get("equipmentId").toString().equals(equipmentDTO.getId())) {
					equipmentDTO.setExceptionCount(Integer.parseInt(per.get("exceptionCount").toString()));
					equipmentDTO.setCurrentStatus(VerificationEnum.TempStates_LOSE_EFFICACY.getMsg());
				}
			}
			equipmentDTOs.add(equipmentDTO);
		}
		pageBean.setList(equipmentDTOs);
		pageBean.setTotal(totalCount);
		return pageBean;
	}

	/**
	 * 根据工厂地点id查询设备
	 * 
	 * @param factoryLocationId
	 * @return
	 */
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<Equipment> queryEquipmentByFactoryLocationId(String factoryLocationId) {
		Example example = new Example(Equipment.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("deleteFlag", VerificationEnum.DELETE_NO.getCode());
		criteria.andEqualTo("factoryLocationId", factoryLocationId);
		List<Equipment> equipmentList = equipmentMapper.selectByExample(example);
		return equipmentList;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public PageBean<ProofDeviceDTO> findBindProofDeviceByFactoryId(String id, PageBean<ProofDeviceDTO> pageBean) {
		int count = proofDeviceMapper.countDeviceBindProof(id);
		if (count == 0) { // 为0 直接返回 不查询
			pageBean.setTotal(count);
			pageBean.setList(new ArrayList<ProofDeviceDTO>());
			return pageBean;
		}
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize(), false);
		List<ProofDevice> proofDevices = proofDeviceMapper.findDeviceBindProof(id);
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
}
