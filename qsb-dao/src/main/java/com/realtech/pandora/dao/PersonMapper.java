package com.realtech.pandora.dao;
import java.util.List;

import com.realtech.pandora.common.mapper.MyMapper;
import com.realtech.pandora.domain.Person;

public interface PersonMapper extends MyMapper<Person> {


	/**
     * 根据防错装置id查询负责人
     * @param id
     * @return
     */
    List<Person> queryPersonJoinLink(String id);



	/**
	 * 查询上传的人员是否合法
	 * @param persons
	 * @return
	 */
	public List<Person> findSubmitPerson(List<String> persons);


	/**
	 * 根据报警装置id查询人员
	 * @param id
	 * @return
	 */
	public List<Person> findPersonByAlertId(String id);



}