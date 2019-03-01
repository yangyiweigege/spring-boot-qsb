package com.realtech.pandora.dao;

import com.realtech.pandora.common.mapper.MyMapper;
import com.realtech.pandora.domain.SystemResource;

import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 资源数据层接口
 * @author yangyiwei
 * @date 2018年8月2日
 * @time 下午4:17:05
 */
public interface SystemResourceMapper extends MyMapper<SystemResource> {

	/**
	 * 根据使用者id查询对应的资源
	 * @param id
	 * @return
	 */
	public SystemResource findResoureByObjId(String id);

    /**
     * 根据防错装置id查询防错装置图片数据
     * @param id
     * @return
     */
    public List<SystemResource> querySystemResourceByDeviceId(String id);

    /**
	 * 根据使用者id更新对应的资源
	 * @param id
	 * @return
	 */
	public int updateResourceByObjId(SystemResource systemResource);
}