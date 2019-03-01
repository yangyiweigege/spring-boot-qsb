package com.realtech.pandora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.MeasureRecord;
import com.realtech.pandora.service.MeasureRecordService;

import io.swagger.annotations.Api;

/**
 * 防错措施记录控制层
 * 
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:04:23
 */
@RestController
@RequestMapping("/measure/record")
@Api(value = "measure-record-controller", description = "防错措施记录表")
public class MeasureRecordController {

	@Autowired
	private MeasureRecordService measureRecordService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseResult<List<MeasureRecord>> list() {
		List<MeasureRecord> list = measureRecordService.findAll();
		return new ResponseResult<List<MeasureRecord>>(ResultStatus.SUCCESS, list);
	}

}
