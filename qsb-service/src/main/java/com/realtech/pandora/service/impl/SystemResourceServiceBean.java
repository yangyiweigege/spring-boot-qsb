package com.realtech.pandora.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.dao.SystemResourceLinkMapper;
import com.realtech.pandora.dao.SystemResourceMapper;
import com.realtech.pandora.domain.SystemResource;
import com.realtech.pandora.domain.SystemResourceLink;
import com.realtech.pandora.service.SystemResourceService;
import com.realtech.pandora.util.DateUtil;
import com.realtech.pandora.util.OidUtil;

@Service
@Transactional
public class SystemResourceServiceBean implements SystemResourceService {

	@Resource
	private SystemResourceMapper systemResourceMapper;
	@Resource
	private SystemResourceLinkMapper systemResourceLinkMapper;
	@Value("${upload.localtion.windows}")
	private String accessUrl;

	
	@Override
	@Transactional
	public ResultStatus insertResource(String id, String imageName) {
		// 第一步 插入资源表
		SystemResource systemResource = new SystemResource();
		String sysId = OidUtil.newId();
		systemResource.setId(sysId);
		systemResource.setCode(sysId);
		systemResource.setAccessUrl(accessUrl + imageName);
		systemResource.setType((short) 1);
		systemResource.setPublisher("杨乙伟");
		systemResource.setStatus((short) 1);
		systemResource.setUploadTime(DateUtil.format(new Date()));
		systemResource.setSize((long) 1);
		systemResource.setCreateAt(DateUtil.format(new Date()));
		systemResource.setCreateBy("创建人");
		systemResource.setDataVersion(1);
		systemResource.setDeleteFlag(0);
		systemResource.setName(imageName);
		systemResource.setStorageUrl(accessUrl + imageName);
		systemResourceMapper.insertSelective(systemResource);
		// 第二步 插入图片关联表
		SystemResourceLink systemResourceLink = new SystemResourceLink();
		systemResourceLink.setId(OidUtil.newId());
		systemResourceLink.setResId(sysId);
		systemResourceLink.setUserObjId(id);
		systemResourceLink.setCreateAt(DateUtil.format(new Date()));
		systemResourceLink.setCreateBy("创建人");
		systemResourceLink.setDeleteFlag(0);
		systemResourceLink.setDataVersion(1);
		systemResourceLinkMapper.insertSelective(systemResourceLink);
		return ResultStatus.SUCCESS;
	}

	@Override
	@Transactional(readOnly = true)
	public SystemResource findResourceById(String id) {
		return systemResourceMapper.findResoureByObjId(id);
	}

	@Override
	public ResultStatus updateResource(String id, String imageName) {
		SystemResource systemResource = new SystemResource();
		systemResource.setName(imageName);
		systemResource.setAccessUrl(accessUrl + imageName);
		systemResource.setId(id);
		systemResource.setStorageUrl(accessUrl + imageName);
		systemResourceMapper.updateResourceByObjId(systemResource);
		return ResultStatus.SUCCESS;
	}

	@Override
	public ResultStatus deleteResource(String id) {
		//
		return null;
	}

}
