package com.realtech.pandora.dao;

import java.util.List;

import com.realtech.pandora.common.mapper.MyMapper;
import com.realtech.pandora.domain.FactoryLocation;

public interface FactoryLocationMapper extends MyMapper<FactoryLocation> {

	/**
	 * 根据倒数第二层地点 查询该地点下虚拟设备
	 * @param id
	 * @return
	 */
	public List<FactoryLocation> findBindDeviceByPid(String pid);
	
	/**
	 * 统计记录数分页使用
	 * @param pid
	 * @return
	 */
	public int findBindDeviceByPidCount(String pid);

	/**
	 * 根据虚拟设备id查询车间
	 * @param id
	 * @return
	 */
	public FactoryLocation queryWorkShopById(String id);
}