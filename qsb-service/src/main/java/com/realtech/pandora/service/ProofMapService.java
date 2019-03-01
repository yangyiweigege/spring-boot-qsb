package com.realtech.pandora.service;

import java.util.List;
import java.util.Map;

import com.realtech.pandora.domain.DTO.ProofMapDTO;

/**
 * 防错地图业务逻辑层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:47:12
 */
public interface ProofMapService {

	/**
	 * 查询防错地图(设备层)
	 * @param id
	 * @return
	 */
	public List<ProofMapDTO> findProofDeviceByEquipment(String id);
	
	/**
	 * 查询防错地图(工程程)
	 * @param id
	 * @return
	 */
	public List<ProofMapDTO> findEquipmentByFactoryId(String id);

}
