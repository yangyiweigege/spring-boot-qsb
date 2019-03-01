package com.realtech.pandora.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.realtech.pandora.domain.DTO.MeasureTemplateDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.dao.MeasureTemplateMapper;
import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.service.MeasureTemplateService;

@Service
@Transactional
public class MeasureTemplateServiceBean implements MeasureTemplateService {
	
	@Resource
	private MeasureTemplateMapper measureTemplateMapper;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<MeasureTemplate> findMeasureTemplatesByEquId(String id) {
		MeasureTemplate search = new MeasureTemplate();
		search.setDeleteFlag(0);
		search.setProofDeviceId(id);
		return measureTemplateMapper.select(search);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ResultStatus deleteTemplateById(String id) {
		MeasureTemplate update = new MeasureTemplate();
		update.setId(id);
		update.setDeleteFlag(1);
		int count = measureTemplateMapper.updateByPrimaryKeySelective(update);
		if (count < 1) {
			return ResultStatus.UPDATE_FAIL;
		}
		return ResultStatus.SUCCESS;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
	public List<MeasureTemplateDTO> findTemplateById(String id) {
		List<MeasureTemplateDTO> list = measureTemplateMapper.findTemplateById(id);
		return list;
	}

}
