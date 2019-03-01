package com.realtech.pandora.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtech.pandora.common.utils.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.annotation.ValidatePage;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.ExcelUtil;
import com.realtech.pandora.common.utils.JVMCache;
import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.domain.Person;
import com.realtech.pandora.domain.ProofDevice;
import com.realtech.pandora.domain.SystemResource;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.ProofDevicePersonLinkService;
import com.realtech.pandora.service.ProofDeviceService;
import com.realtech.pandora.service.SystemResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 防错装置控制层
 *
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:40:01
 */
@RestController
@RequestMapping("/proof/device")
@Api(value = "Error-Record-Controller", description = "防错装置")
@Slf4j
public class ProofDeviceController {

	@Autowired
	private ProofDeviceService proofDeviceService;
	@Autowired
	private ProofDevicePersonLinkService proofDevicePersonLinkService;
	@Autowired
	private SystemResourceService systemResourceService;


	/*
	 * 参数格式如下传递： { "proofDevice":{
	 * "name":"杨哥测试机","code":"shb-0512","process":"写代码","factoryId":
	 * "JOCXLE6I0Y5HU9NFNY2I", "status":1,
	 * "isItem":1,"failureMode":"有水失效","maintenanceTime":1,"maintenanceTimeUnit"
	 * :1,"maintenanceTimeNum":1,
	 * "verificationTime":1,"verificationTimeUnit":1,"verificationTimeNum":1,
	 * "verificationMethod":0,
	 * "proofCategory":0,"purpose":"防止手拉下滑","desc":"卡一下位置",
	 * "installationTime":"2017-01-23 00:00:00" "proofType":1 },
	 * "measureTemplates":[{"verificationItem":"随便验证一下拉","verificationContent":
	 * "看看字段复合不","faultPerformance":"故障表先一下拉","emergencyResponse":"看看应急","note":
	 * "备注看看"},
	 * {"verificationItem":"随便验证一下拉2222","verificationContent":"看看字段复合222不",
	 * "faultPerformance":"故障表先2222一下拉","emergencyResponse":"看看应222急","note":
	 * "备注看222看"} ], "persons":["JPGY2B5R3OEG6V0R6BSR"},"JPGY2B5R61GPDTSIQLZB",
	 * "JPGY2B5RCGRFRHX1MCA6"] }
	 * "imageName":xxxx.png
	 */
	@ApiOperation(value = "新增防错装置")
	@ApiImplicitParam(name = "jsonObject", value = "防错装置,措施,责任人", required = true, dataType = "jsonObject")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@ValidateJSON(attributes = {"proofDevice"})
	public ResponseResult<String> add(@RequestBody JSONObject jsonObject, String _pandora_p_acc_) {
		log.info("获取到的令牌:{}",  _pandora_p_acc_);
		log.info("获取到的防错设施数据:" + jsonObject.toJSONString());
		// 获取转换的防错装置对象
		JSONObject proofDeviceObject = jsonObject.getJSONObject("proofDevice");
		if (proofDeviceObject == null) {
			log.info("防错装置数据空了.....");
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "防错装置不为空");
		}
		ProofDevice proofDevice = JSONObject.parseObject(proofDeviceObject.toJSONString(), ProofDevice.class);
		// 获取防错措施对象（List）
		JSONArray measureTemplatesArray = jsonObject.getJSONArray("measureTemplates");
		if (measureTemplatesArray == null) {
			log.info("防错措施数据空了.....");
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "防错措施不为空");
		}
		List<MeasureTemplate> measureTemplates = JSONObject.parseArray(measureTemplatesArray.toJSONString(),
				MeasureTemplate.class);
		// 获取责任人链表
		JSONArray personsArray = jsonObject.getJSONArray("persons");
		if (personsArray == null) {
			log.info("责任人数据空了.....");
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "责任人不为空");
		}
		List<String> persons = JSONObject.parseArray(personsArray.toJSONString(), String.class);
		ResultStatus resultStatus = proofDeviceService.addProofDevice(proofDevice, measureTemplates, persons, JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_));
		if (resultStatus.getCode() == 0) { //成功则带出id
			String imageName = jsonObject.getString("imageName");
			if (imageName != null && !"".equals(imageName)) { //插入图片表
				systemResourceService.insertResource(proofDevice.getId(), imageName);
			}
			return new ResponseResult<String>(resultStatus, proofDevice.getId());
		}
		return new ResponseResult<String>(resultStatus);
	}

	@ApiOperation(value = "根据id查询防错装置详情")
	@ApiImplicitParam(name = "id", value = "防错装置id", required = true, dataType = "String", paramType = "query")
	@GetMapping(value = "/detail")
	@ValidatePage
	@ValidateAttribute(attributes = {"id"})
	public ResponseResult<ProofDeviceDTO> findById(ProofDeviceDTO proofDeviceDTO, PageBean<ProofDeviceDTO> page) {
		log.info("接收到的防错装置id:{}", proofDeviceDTO.getId());
		List<ProofDeviceDTO> list = proofDeviceService.queryErrorRecord(proofDeviceDTO, page);
		if (list.size() == 0) { // 未查询到数据
			return new ResponseResult<ProofDeviceDTO>(ResultStatus.DATA_NOT_FIND);
		}
		// 继续查找对应的责任人链表
		List<Person> persons = proofDevicePersonLinkService.findPersonsByProofId(proofDeviceDTO.getId());
		list.get(0).setPersonList(persons);
		list.get(0).setImageName("");
		//继续查找该装置对应的图片
		SystemResource systemResource = systemResourceService.findResourceById(proofDeviceDTO.getId());
		if (systemResource != null) {
			list.get(0).setImageName(systemResource.getName());
		}
		return new ResponseResult<ProofDeviceDTO>(ResultStatus.SUCCESS, list.get(0));
	}


	@ApiOperation(value = "编辑防错装置")
	@ApiImplicitParam(name = "jsonObject", value = "防错装置,措施,责任人", required = true, dataType = "jsonObject")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseResult<?> edit(@RequestBody JSONObject jsonObject, String _pandora_p_acc_) {
		log.info("获取到的防错设施数据:" + jsonObject.toJSONString());
		// 获取转换的防错装置对象
		JSONObject proofDeviceObject = jsonObject.getJSONObject("proofDevice");
		if (proofDeviceObject == null) {
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "防错装置不为空");
		}
		ProofDevice proofDevice = JSONObject.parseObject(proofDeviceObject.toJSONString(), ProofDevice.class);
		if (proofDevice.getId() == null || "".equals(proofDevice.getId())) {
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "防错装置Id不为空");
		}
		// 获取防错措施对象（List）
		JSONArray measureTemplatesArray = jsonObject.getJSONArray("measureTemplates");
		if (measureTemplatesArray == null) {
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "防错措施不为空");
		}
		List<MeasureTemplate> measureTemplates = JSONObject.parseArray(measureTemplatesArray.toJSONString(),
				MeasureTemplate.class);
		// 获取责任人链表
		JSONArray personsArray = jsonObject.getJSONArray("persons");
		if (personsArray == null) {
			return new ResponseResult<String>(ResultStatus.LACK_PARAM, "责任人不为空");
		}
		List<String> persons = JSONObject.parseArray(personsArray.toJSONString(), String.class);
		ResultStatus resultStatus = proofDeviceService.editProofDevice(proofDevice, measureTemplates, persons, JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_));
		if (resultStatus.getCode() == 0) {
			String imageName = jsonObject.getString("imageName");
			if (imageName != null && !"".equals(imageName)) { //插入图片表
				systemResourceService.updateResource(proofDevice.getId(), imageName);
			} else { // 删除图片资源
				systemResourceService.updateResource(proofDevice.getId(), "");
			}
		}
		return new ResponseResult<>(resultStatus);
	}

	@ApiOperation(value = "删除防错装置")
	@ApiImplicitParam(name = "id", value = "装置id", required = true, dataType = "String")
	@GetMapping("/del")
	@ValidateAttribute(attributes = {"id"})
	public ResponseResult<?> deleteProofDevice(String id) {
		ResultStatus resultStatus = proofDeviceService.deleteProofDeviceById(id);
		return new ResponseResult<>(resultStatus);
	}

	/**
	 * 导出excel数据
	 *
	 * @param response
	 * @param request
	 */
	@GetMapping(value = "/export")
	@ApiOperation(value = "导出Excel数据", httpMethod = "GET")
	public ResponseResult<?> exportExcel(HttpServletResponse response, HttpServletRequest request, String _pandora_p_acc_) {
		LoginUserInfo loginUserInfo = JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_);
		List<ProofDeviceDTO> list = proofDeviceService.exportProofList(loginUserInfo.getFactoryId());
		if (CollectionUtils.isEmpty(list)) {
			return new ResponseResult<>(ResultStatus.EXCEL_EXPORT_ERROR);
		} else {
			ExcelUtil.exportExcel(list, "防错清单", "防错清单", ProofDeviceDTO.class, "防错清单.xls", response, request);
			return new ResponseResult<>(ResultStatus.SUCCESS);
		}
	}

	/**
	 * 查询防错清单列表
	 *
	 * @return
	 */
	@ApiOperation(value = "查询防错清单列表")
	@GetMapping(value = "/queryProofList")
	public ResponseResult<List<ProofDeviceDTO>> queryProofList(String _pandora_p_acc_) {
		LoginUserInfo loginUserInfo = JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_);
		List<ProofDeviceDTO> list = proofDeviceService.exportProofList(loginUserInfo.getFactoryId());
		return new ResponseResult<List<ProofDeviceDTO>>(ResultStatus.SUCCESS, list);
	}

}
