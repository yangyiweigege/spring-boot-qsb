package com.realtech.pandora.service;

import java.util.List;

import com.realtech.pandora.domain.Person;

/**
 * 防错-责任人 业务逻辑层
 * @author yangyiwei
 * @date 2018年7月23日
 * @time 下午3:26:21
 */
public interface ProofDevicePersonLinkService {

	/**
	 * 根据防错装置id查询对应的责任人
	 * @param id
	 * @return
	 */
	public List<Person> findPersonsByProofId(String id);

}
