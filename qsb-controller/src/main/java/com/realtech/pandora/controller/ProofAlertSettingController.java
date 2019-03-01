package com.realtech.pandora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.DTO.ProofAlertSettingDTO;
import com.realtech.pandora.service.ProofAlertSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 防错装置配置控制层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:55:55
 */
@Api(value = "proof-alert-setting-controller" , description = "防错装置配置控制层")
@RestController
@RequestMapping("/proof/alert/setting")
@Slf4j
public class ProofAlertSettingController {
	
	@Autowired
	private ProofAlertSettingService proofAlertSettingService;
	
	/**
	 * 要求格式
	 * {
	 * 		proofAlertSettings:[{"notifyTimeLimit":1231233132,"notifyTimeLimitUom":1,"type":1,"persons":["JPGY2B5R3OEG6V0R6BSR"},"JPGY2B5R61GPDTSIQLZB","JPGY2B5RCGRFRHX1MCA6"]]},{},{}   ],
	 * 		proofDeviceId:1
	 * }
	 * @param proofAlertSettings
	 * @return
	 */
	@ApiOperation("添加防错报警装置")
	@ApiImplicitParam(name = "proofAlertSettings", value = "防错报警装置列表", required = true, dataType = "list")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseResult<?> add(@RequestBody List<ProofAlertSettingDTO> proofAlertSettings) {
		log.info("获取到的报警数据列表：{}", JSONObject.toJSONString(proofAlertSettings));
		
		if (proofAlertSettings.size() > 3 || proofAlertSettings.size() < 1) {
			log.info("用户上传的防错报警列表长度大于3或者长度为0......");
			return new ResponseResult<String>(ResultStatus.ALERT_CAN_NOT_GT_THREE);
		}
		ResultStatus resultStatus = proofAlertSettingService.addAlertSettings(proofAlertSettings);
		return new ResponseResult<>(resultStatus);	
	}
	
	@ApiOperation("查询防错报警装置")
	@ApiImplicitParam(name = "id", value = "防错装置id", required = true, dataType = "String")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ValidateAttribute(attributes = {"id"})
	public ResponseResult<List<ProofAlertSettingDTO>> findListByProofId(String id) {
		log.info("获取到的报警装置id：{}", id);
		List<ProofAlertSettingDTO> proofAlertSettingDTOs = proofAlertSettingService.findListByProofId(id);
		return new ResponseResult<List<ProofAlertSettingDTO>>(ResultStatus.SUCCESS, proofAlertSettingDTOs);	
	}
	
	
	@ApiOperation("编辑防错报警装置")
	@ApiImplicitParam(name = "proofAlertSettings", value = "防错报警装置列表", required = true, dataType = "list")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseResult<?> edit(@RequestBody List<ProofAlertSettingDTO> proofAlertSettings) {
		log.info("获取到的报警数据列表：{}", JSONObject.toJSONString(proofAlertSettings));	
		if (proofAlertSettings.size() > 3 || proofAlertSettings.size() < 1) {
			log.info("用户上传的防错报警列表长度大于3或者长度为0......");
			return new ResponseResult<String>(ResultStatus.ALERT_CAN_NOT_GT_THREE);
		}
		ResultStatus resultStatus = proofAlertSettingService.editAlertSettings(proofAlertSettings);
		return new ResponseResult<>(resultStatus);	
	}

}
