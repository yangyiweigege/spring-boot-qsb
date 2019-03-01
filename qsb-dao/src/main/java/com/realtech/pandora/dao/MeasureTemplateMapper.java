package com.realtech.pandora.dao;

import com.realtech.pandora.domain.DTO.MeasureTemplateDTO;
import com.realtech.pandora.domain.MeasureTemplate;

import java.util.List;

import com.realtech.pandora.common.mapper.MyMapper;

public interface MeasureTemplateMapper extends MyMapper<MeasureTemplate> {
	
	/**
	 * 批量插入防错措施表
	 * @param measureTemplates
	 */
	public int insertBatch(List<MeasureTemplate> measureTemplates);

	/**
	 * 根据防错装置id查询模板
	 * @param id
	 * @return
	 */
	public List<MeasureTemplateDTO> findTemplateById(String id);
}