package com.realtech.pandora.service;

import java.util.List;

import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.DTO.MeasureTemplateDTO;
import com.realtech.pandora.domain.MeasureTemplate;

/**
 * 防错模板业务逻辑层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:50:32
 */
public interface MeasureTemplateService {

	/**
	 * 根据防错装置id 查询防错措施模板
	 * @param id
	 * @return
	 */
	public List<MeasureTemplate> findMeasureTemplatesByEquId(String id);

	/**
	 * 根据id删除防错措施模板
	 * @param id
	 * @return
	 */
	public ResultStatus deleteTemplateById(String id);

	/**
	 * 根据防错装置id查询防错措施模板
	 * @param id
	 * @return
	 */
	public List<MeasureTemplateDTO> findTemplateById(String id);

}
