package com.realtech.pandora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.annotation.ValidatePage;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.EquipmentService;
import com.realtech.pandora.service.ProofDeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备控制层
 * 
 * @author yangyiwei
 * @date 2018年7月25日
 * @time 下午3:01:07
 */
@RestController
@RequestMapping("/equipment")
@Api(value = "equipment-controller", description = "设备控制层")
@Slf4j
public class EquipmentController {

	/*@Autowired
	private EquipmentService equipmentService;*/
	@Autowired
	private ProofDeviceService proofDeviceService;

	@GetMapping(value = "/bind/proof/device")
	@ApiOperation(value = "根据虚拟地点id查询真实设备对应的防错装置", httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "工厂虚拟设备id", required = true, dataType = "String")
	@ValidatePage
	@ValidateAttribute(attributes = { "id" })
	public ResponseResult<PageBean<ProofDeviceDTO>> getBindProofDevice(String id, PageBean<ProofDeviceDTO> pageBean) {
		log.info("获取到的虚拟地点id是 {}", id);
		pageBean = proofDeviceService.findBindProofDevice(id, pageBean);
		return new ResponseResult<PageBean<ProofDeviceDTO>>(ResultStatus.SUCCESS, pageBean);
	}

}
