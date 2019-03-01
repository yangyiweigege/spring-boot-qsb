package com.realtech.pandora.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.realtech.pandora.dao.PersonMapper;
import com.realtech.pandora.domain.Person;
import com.realtech.pandora.service.ProofDevicePersonLinkService;

@Service
@Transactional
public class ProofDevicePersonLinkServiceBean implements ProofDevicePersonLinkService {
	
	@Resource
	private PersonMapper personMapper;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<Person> findPersonsByProofId(String id) {
		List<Person> persons = personMapper.queryPersonJoinLink(id);
		return persons;
	}

}
