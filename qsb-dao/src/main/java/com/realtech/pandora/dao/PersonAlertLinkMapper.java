package com.realtech.pandora.dao;

import com.realtech.pandora.domain.PersonAlertLink;
import com.realtech.pandora.common.mapper.MyMapper;

public interface PersonAlertLinkMapper extends MyMapper<PersonAlertLink> {

	/**
	 * 根据防错装置id删除报警-关联人员
	 * @param id
	 * @return
	 */
	public int deleteByProofDeviceId(String id);
}