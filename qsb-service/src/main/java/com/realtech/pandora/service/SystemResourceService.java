package com.realtech.pandora.service;

import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.SystemResource;

/**
 * 系统资源业务逻辑层
 * @author yangyiwei
 * @date 2018年8月2日
 * @time 下午2:16:24
 */
public interface SystemResourceService {

	/**
	 * 将图片资源插入系统
	 * @param id
	 * @param imageName
	 * @return
	 */
	public ResultStatus insertResource(String id, String imageName);

	/**
	 * 根据使用对象id查询资源
	 * @param id
	 * @return
	 */
	public SystemResource findResourceById(String id);

	/**
	 * 更新图片资源
	 * @param id
	 * @param imageName
	 * @return
	 */
	public ResultStatus updateResource(String id, String imageName);

	/**
	 * 删除资源根据id
	 * @param id
	 */
	public ResultStatus deleteResource(String id);

}
