package com.realtech.pandora.dao;

import com.realtech.pandora.domain.ProofMap;
import com.realtech.pandora.domain.DTO.ProofMapDTO;

import java.util.List;

import com.realtech.pandora.common.mapper.MyMapper;

public interface ProofMapMapper extends MyMapper<ProofMap> {

	/**
	 * 形成设备防错地图（设备层）
	 * @param id 虚拟设备id
	 * @return
	 */
	public List<ProofMapDTO> findProofDeviceByEquipment(String id);

	/**
	 * 形成设备防错地图(工段层)
	 * @param id 地点id
	 * @return
	 */
	public List<ProofMapDTO> findByPid(String id);
}