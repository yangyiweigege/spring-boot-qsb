package com.realtech.pandora.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.utils.JVMCache;
import com.realtech.pandora.common.utils.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.realtech.pandora.common.annotation.ValidateAttribute;
import com.realtech.pandora.common.annotation.ValidatePage;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.ExcelUtil;
import com.realtech.pandora.domain.Person;
import com.realtech.pandora.domain.DTO.MeasureRecordDTO;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.page.PageBean;
import com.realtech.pandora.service.MeasureRecordService;
import com.realtech.pandora.service.ProofDeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 出错记录
 * Created by zhuqq
 */
@Controller
@RequestMapping(value = "/error")
@Slf4j 
@Api(value = "Error-Record-Controller", description = "防错记录")
public class ErrorRecordController {

    @Autowired
    private ProofDeviceService proofDeviceService;

   /* @Autowired
    private EquipmentService equipmentService;*/

    @Autowired
    private MeasureRecordService measureRecordService;

    /**
     * 根据条件查询防错记录
     * @param proofDeviceDTO
     * @param page
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/recordList")
    @ApiOperation(value = "根据条件查询防错记录", httpMethod ="GET")
    @ValidatePage
    public ResponseResult<PageBean> errorRecordList(ProofDeviceDTO proofDeviceDTO,PageBean
            page, String _pandora_p_acc_) {
        log.info("proofDeviceDTO :" + proofDeviceDTO.toString());

        //当前端传来设备id的条件时
        if (!StringUtils.isEmpty(proofDeviceDTO.getFactoryLocationId())) {
            proofDeviceDTO.setEquipmentId(proofDeviceDTO.getFactoryLocationId());
            /*List<Equipment> equipmentList = equipmentService.queryEquipmentByFactoryLocationId(proofDeviceDTO
                    .getFactoryLocationId());
            //虚拟设备与真实设备一对一
            if (equipmentList.size() == 1) {
                proofDeviceDTO.setEquipmentId(equipmentList.get(0).getId());
            } else {
                return new ResponseResult<PageBean>(ResultStatus.SUCCESS, new PageBean<ProofDeviceDTO>(null));
            }*/
        }
        LoginUserInfo loginUserInfo = JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_);
        //JQ303EHVOAEH5NBZ45CR为集团用户
        if (!loginUserInfo.getFactoryId().equals(VerificationEnum.FACTORY_ID.getMsg())) {
            proofDeviceDTO.setFactoryId(loginUserInfo.getFactoryId());
        }
        List<ProofDeviceDTO> list = proofDeviceService.queryErrorRecord(proofDeviceDTO, page);
        return new ResponseResult<PageBean>(ResultStatus.SUCCESS, new PageBean<ProofDeviceDTO>(list));
    }

    /**
     * 根据名字模糊查询人员表
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/personList")
    @ApiOperation(value = "根据名字模糊查询人员表", httpMethod ="GET")
    @ValidatePage
    public ResponseResult<PageBean> queryPersonList(String name, PageBean page) {
        log.info("personList name:"+name);
        List<Person> list = proofDeviceService.queryPersonList(name, page);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseResult<>(ResultStatus.DATA_NOT_FIND);
        }
        return new ResponseResult<PageBean>(ResultStatus.SUCCESS, new PageBean<Person>(list));
    }

    /**
     * 保存防错措施记录
     * @param jsonObject
     * eg:{
     *     MeasureRecordDTO:[{"id":"",""verificationItem":"","verificationContent":"",
     *     "faultPerformance":"", "bach": 批次号,
     *     "emergencyResponse":"","reportPersons":"","reportTime":"","fixPersons":"","fixTime":"","Terminal":""},
     *     {""id":"",verificationItem":"","verificationContent":"","faultPerformance":"", "bach": 批次号,
     *     "emergencyResponse":"","reportPersons":"","reportTime":"","fixPersons":"","fixTime":"","Terminal":""}],
     *     "proofDeviceId":""
     *     "status":""
     * }
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/saveMeasureRecord")
    @ApiOperation(value = "保存防错措施记录", httpMethod ="POST")
    @ApiImplicitParam(name = "jsonObject", value = "防错措施记录", required = true, dataType = "jsonObject")
    public ResponseResult<?> saveMeasureRecord(@RequestBody JSONObject jsonObject, String _pandora_p_acc_) {
        log.info("saveMeasureRecord jsonObject:"+jsonObject);
        JSONArray measureRecordList = jsonObject.getJSONArray("MeasureRecordDTO");
        String proofDeviceId = jsonObject.getString("proofDeviceId");
        Integer status = jsonObject.getInteger("status");
        if (StringUtils.isEmpty(proofDeviceId)) {
            return  new ResponseResult<>(ResultStatus.LACK_PARAM);
        }
        List<MeasureRecordDTO> measureRecordDTOList = null;
        if (measureRecordList != null) {
            measureRecordDTOList = JSONObject.parseArray(measureRecordList.toJSONString(), MeasureRecordDTO.class);
        }
        ResultStatus resultStatus = measureRecordService.addMeasureRecord(measureRecordDTOList, proofDeviceId, status, JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_));
        return new ResponseResult<>(resultStatus);
    }

    /**
     * 删除防错措施记录
     * @param id 防错措施id
     * @return
     */
//    @ResponseBody
//    @PostMapping(value = "/delMeasureRecord")
//    @ApiOperation(value = "删除防错措施记录", httpMethod ="POST")
//    @ApiImplicitParam(name = "String", value = "防错措施记录id", required = true)
//    public ResponseResult<?> delMeasureRecord(String id) {
//        log.info("delMeasureRecord id:"+id);
//        ResultStatus resultStatus = measureRecordService.delMeasureRecord(id);
//        return new ResponseResult<>(resultStatus);
//    }

    /**
     * 根据防错装置id查询防错措施记录
     * @param proofDeviceId
     * @param status                    装置当前状态
     * @param page
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/queryMeasureRecord")
    @ApiOperation(value = "根据防错装置id查询防错措施记录", httpMethod ="GET")
    @ValidatePage
    @ValidateAttribute( attributes = {"status","proofDeviceId"})
    public ResponseResult<List<MeasureRecordDTO>> queryMeasureRecord(String proofDeviceId, Integer status, PageBean page) {
        log.info("proofDeviceId:" +proofDeviceId);
        List<MeasureRecordDTO> list = measureRecordService.queryMeasureRecord(proofDeviceId, status, page);
        return new ResponseResult<List<MeasureRecordDTO>>(ResultStatus.SUCCESS, list);
    }

    /**
     * 导出excel数据
     * @param proofDeviceId
     * @param status                    装置当前状态
     * @param response
     * @param request
     */
    @GetMapping(value = "/export")
    @ApiOperation(value = "导出Excel数据", httpMethod ="GET")
    @ResponseBody
    @ValidateAttribute( attributes = {"status","proofDeviceId"})
    public ResponseResult export(HttpServletResponse response, HttpServletRequest request, String proofDeviceId, Integer status) {
        List<MeasureRecordDTO> list = measureRecordService.queryMeasureRecord(proofDeviceId, status, null);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseResult(ResultStatus.EXCEL_EXPORT_ERROR);
        } else {
            ExcelUtil.exportExcel(list,"出错记录","出错记录",MeasureRecordDTO.class,"出错记录.xls",response, request);
            return new ResponseResult(ResultStatus.SUCCESS);
        }
    }
}
