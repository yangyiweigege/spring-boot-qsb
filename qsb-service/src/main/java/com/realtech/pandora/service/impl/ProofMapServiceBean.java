package com.realtech.pandora.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realtech.pandora.dao.EquipmentMapper;
import com.realtech.pandora.dao.ProofDeviceMapper;
import com.realtech.pandora.dao.ProofMapMapper;
import com.realtech.pandora.domain.DTO.ProofMapDTO;
import com.realtech.pandora.service.ProofMapService;

@Service
@Transactional
public class ProofMapServiceBean implements ProofMapService {

	@Resource
	private ProofMapMapper proofMapMapper;
	@Resource
	private EquipmentMapper equipmentMapper;
	@Resource
	private ProofDeviceMapper proofDeviceMapper;

	@Override
	@Transactional(readOnly = true)
	public List<ProofMapDTO> findProofDeviceByEquipment(String id) {
		// id为虚拟id
		List<ProofMapDTO> list = proofMapMapper.findProofDeviceByEquipment(id);
		for (ProofMapDTO item : list) {
			if (item.getStatus() == 0) {
				item.setColor("green");
			} else if (item.getStatus() == 1) {
				item.setColor("red");
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProofMapDTO> findEquipmentByFactoryId(String id) {
		// 查询地段对应的防错节点
		List<ProofMapDTO> proofMapDTOs = proofMapMapper.findByPid(id);
		if (proofMapDTOs.isEmpty()) {
			return new ArrayList<ProofMapDTO>(0);
		}
		// 第三步，对真实设备绑定的防错设备节点进行分组统计
		List<String> equipmentIds = new ArrayList<String>();
		for (ProofMapDTO item : proofMapDTOs) {
			equipmentIds.add(item.getObjectId());
		}
		List<Map<String, Object>> groupMap = proofDeviceMapper.groupByEquipmentIds(equipmentIds);
		// 最终拼接数据，得到结果(设备对应的防错节点异常个数)
		for (ProofMapDTO item : proofMapDTOs) {
			for (Map<String, Object> per : groupMap) { // 查询防错节点
				// 如果相等，则说明该设备有异常防错节点
				if (per.get("equipmentId").toString().equals(item.getObjectId())) {
					item.setColor("red");
					item.setStatus(1);
				}
			}
		}
		return proofMapDTOs;
	}

}
