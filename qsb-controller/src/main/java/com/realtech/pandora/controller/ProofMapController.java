package com.realtech.pandora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.DTO.ProofMapDTO;
import com.realtech.pandora.service.ProofMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 防错地图业务控制层
 * 
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:46:35
 */

@RestController
@RequestMapping("/proof/map")
@Slf4j
@Api(value = "proof-map-controller", description = "防错地图控制层")
public class ProofMapController {

	@Autowired
	private ProofMapService proofMapService;

	/**
	 * 数据库表 t_qms_proof_map 中属性 pid:虚拟设备id type=5 object_id 防错装置id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "防错装置地图(设备层)")
	@ApiImplicitParam(name = "id", value = "虚拟设备id", required = true, dataType = "String")
	@GetMapping("/bind/device")
	@ValidateAttribute(attributes = { "id" })
	public ResponseResult<List<ProofMapDTO>> findProofDeviceByEquipment(String id) {
		log.info("获取到的虚拟设备id:{}", id);
		List<ProofMapDTO> list = proofMapService.findProofDeviceByEquipment(id);
		return new ResponseResult<List<ProofMapDTO>>(ResultStatus.SUCCESS, list);
	}

	/**
	 * 数据库表 t_qms_proof_map 中属性 pid:工厂地点id type!=5 object_id
	 * 真实设备id(equipment表中id)
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "防错装置地图(工段层)")
	@ApiImplicitParam(name = "id", value = "工厂地点id", required = true, dataType = "String")
	@GetMapping("/bind/equipment")
	@ValidateAttribute(attributes = { "id" })
	public ResponseResult<List<ProofMapDTO>> findEquipmentByFactoryId(String id) {
		log.info("获取到的工厂id:{}", id);
		List<ProofMapDTO> proofMapDTOs = proofMapService.findEquipmentByFactoryId(id);
		return new ResponseResult<>(ResultStatus.SUCCESS, proofMapDTOs);
	}

}
