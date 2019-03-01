package com.realtech.pandora.service;

import com.realtech.pandora.domain.Equipment;
import com.realtech.pandora.domain.FactoryLocation;
import com.realtech.pandora.domain.DTO.EquipmentDTO;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.page.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 设备层业务逻辑层
 * @author yangyiwei
 * @date 2018年7月20日
 * @time 上午11:08:41
 */
public interface EquipmentService {

	/**
	 * 获取某个地点下所有设备以及防错装置点
	 * @param id
	 * @param pageNum 
	 * @param pageSize 
	 * @return
	 */
	public PageBean<EquipmentDTO> getDeviceAndProof(String id, PageBean<EquipmentDTO> pageBean);

	/**
	 * 根据工厂地点id查询设备
	 * @param factoryLocationId
	 * @return
	 */
	public List<Equipment> queryEquipmentByFactoryLocationId(String factoryLocationId);

	/**
	 * 获取某个设备下所有防错装置点
	 * @param id
	 * @param pageBean
	 * @return
	 */
	public PageBean<ProofDeviceDTO> findBindProofDeviceByFactoryId(String id, PageBean<ProofDeviceDTO> pageBean);

}
