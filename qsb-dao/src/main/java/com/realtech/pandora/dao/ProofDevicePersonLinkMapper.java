package com.realtech.pandora.dao;

import java.util.List;

import com.realtech.pandora.common.mapper.MyMapper;
import com.realtech.pandora.domain.ProofDevicePersonLink;

public interface ProofDevicePersonLinkMapper extends MyMapper<ProofDevicePersonLink> {

	/**
	 * 批量插入责任人-防错装置
	 * @param links
	 */
	public int insertBatch(List<ProofDevicePersonLink> links);

	
}