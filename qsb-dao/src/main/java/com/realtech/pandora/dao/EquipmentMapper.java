package com.realtech.pandora.dao;

import java.util.List;

import com.realtech.pandora.common.mapper.MyMapper;
import com.realtech.pandora.domain.Equipment;

/**
 * 设备数据层
 * @author yangyiwei
 * @date 2018年7月24日
 * @time 下午7:14:59
 */
public interface EquipmentMapper extends MyMapper<Equipment> {

	/**
	 * 虚拟设备--》真实设备的转换
	 * @param factoryLocationIds
	 * @return
	 */
	public List<Equipment> findRealDeviceByFactoryIds(List<String> factoryLocationIds);

	/**
	 * 根据proof-map表中的pid 查询该层级对应的真实设备
	 * @param id
	 * @return
	 */
	public List<Equipment> findEquipmentByPid(String id);


}