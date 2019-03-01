package com.realtech.pandora.dao;

import com.realtech.pandora.domain.Person;
import com.realtech.pandora.domain.PersonProofLink;
import com.realtech.pandora.common.mapper.MyMapper;

import java.util.List;

public interface PersonProofLinkMapper extends MyMapper<PersonProofLink> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatch(List<PersonProofLink> list);

    /**
     * 根据引用对象id查询查询人员
     * @param objectId
     * @return
     */
    public List<Person> queryPersonListJoinLink(String objectId);
}