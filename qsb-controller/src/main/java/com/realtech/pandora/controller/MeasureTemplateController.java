package com.realtech.pandora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.service.MeasureTemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 防错措施模板控制层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:49:08
 */
@Api(value = "measure-template-controller", description = "防错措施模板")
@RestController
@RequestMapping("/measure/template")
@Slf4j
public class MeasureTemplateController {
	
	@Autowired
	private MeasureTemplateService measureTemplateService;
	
	
	@ApiOperation(value = "根据防错id查询防错装置模板",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "防错装置id", required = true, dataType = "String", paramType = "query")
	@GetMapping(value = "/list")
	@ValidateAttribute(attributes = {"id"})
	public ResponseResult<List<MeasureTemplate>> getTemplateByEquId(String id) {	
		log.info("接收到的防错装置id:{}", id);
		List<MeasureTemplate> measureTemplates = measureTemplateService.findMeasureTemplatesByEquId(id);
		return new ResponseResult<List<MeasureTemplate>>(ResultStatus.SUCCESS, measureTemplates);	
	}
	
	@ApiOperation(value = "根据id删除防错措施",httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "防错模板id", required = true, dataType = "String", paramType = "query")
	@GetMapping(value = "/del")
	@ValidateAttribute(attributes = {"id"})
	public ResponseResult<?> deleteTemplate(String id) {
		log.info("接收到的防错模板id:{}", id);
		ResultStatus resultStatus = measureTemplateService.deleteTemplateById(id);
		return new ResponseResult<>(resultStatus);
	}

}
