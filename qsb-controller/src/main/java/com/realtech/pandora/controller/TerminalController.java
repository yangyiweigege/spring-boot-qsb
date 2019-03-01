package com.realtech.pandora.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.DTO.MeasureRecordDTO;
import com.realtech.pandora.domain.DTO.MeasureTemplateDTO;
import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.Equipment;
import com.realtech.pandora.domain.MeasureRecord;
import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.domain.ProofDevice;
import com.realtech.pandora.service.MeasureRecordService;
import com.realtech.pandora.service.MeasureTemplateService;
import com.realtech.pandora.service.ProofDeviceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by zhuqq
 */
@RestController
@Slf4j
@Api(value = "Terminal-Controller", description = "Java与终端交互")
public class TerminalController {

    @Autowired
    private MeasureRecordService measureRecordService;

    @Autowired
    private MeasureTemplateService measureTemplateService;

    @Autowired
    private ProofDeviceService proofDeviceService;

    /**
     * 防错验证下发
     * {
     *    "requestId":"xxx",
     *    "operation":" QS_GET_PV_ORDER ",
     *    "data":[{
     *       "code":"xxx"
     *    }],
     *    "requester":"01",
     *    "requestTime":"2018-05-07 08:00:00"
     * }
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/get_pv_order")
    public ResponseResult<?> GetPvOrder(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:" + jsonObject);
        JSONArray array = jsonObject.getJSONArray("data");
        List<Equipment> equipments = JSONObject.parseArray(array.toString(), Equipment.class);
        if (CollectionUtils.isEmpty(equipments))
            return new ResponseResult<List<Equipment>>(ResultStatus.LACK_PARAM);
        List<ProofDevice> list = proofDeviceService.findProofDeviceByCode(equipments.get(0).getCode());
        return new ResponseResult<List<ProofDevice>>(ResultStatus.SUCCESS, list);
    }


    /**
     * 防错验证详情下发
     * {
     *    "requestId":"xxx",
     *    "operation":" QS_GET_PV_DETAIL ",
     *    "data":[{
     *       "id":"xxx"
     *    }],
     *    "requester":"01",
     *    "requestTime":"2018-05-07 08:00:00"
     * }
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/get_pv_detail")
    public ResponseResult<?> GetPvDetail(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:" + jsonObject);
        JSONArray array = jsonObject.getJSONArray("data");
        List<ProofDevice> proofDeviceList = JSONObject.parseArray(array.toString(), ProofDevice.class);
        if (CollectionUtils.isEmpty(proofDeviceList))
            return new ResponseResult<List<ProofDeviceDTO>>(ResultStatus.LACK_PARAM);
        List<ProofDeviceDTO> list = proofDeviceService.findProofDeviceById(proofDeviceList.get(0).getId());
        return new ResponseResult<List<ProofDeviceDTO>>(ResultStatus.SUCCESS, list);
    }

    /**
     * 防错验证模板下发
     * {
     *    "requestId":"xxx",
     *    "operation":" QS_GET_PV_TEMPLATE ",
     *    "data":[{
     *       "id":"xxx",
     *       "status":0,
     *    }],
     *    "requester":"01",
     *    "requestTime":"2018-05-07 08:00:00"
     * }
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/get_pv_template")
    public ResponseResult<?> GetPvTemplate(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:" + jsonObject);
        JSONArray array = jsonObject.getJSONArray("data");
        List<ProofDevice> proofDeviceList = JSONObject.parseArray(array.toString(), ProofDevice.class);
        if (CollectionUtils.isEmpty(proofDeviceList))
            return new ResponseResult<List<ProofDeviceDTO>>(ResultStatus.LACK_PARAM);
        if (proofDeviceList.get(0).getStatus() == VerificationEnum.TempStates_LOSE_EFFICACY.getCode()) {
            List<MeasureRecord> list = measureRecordService.findRecordByProofDeviceIdWithBach(proofDeviceList.get(0).getId());
            return new ResponseResult<List<MeasureRecord>>(ResultStatus.SUCCESS, list);
        } else {
            List<MeasureTemplateDTO> list = measureTemplateService.findTemplateById(proofDeviceList.get(0).getId());
            return new ResponseResult<List<MeasureTemplateDTO>>(ResultStatus.SUCCESS, list);
        }
    }

    /**
     * 修复完成上报
     * {
     *    "requestId":"xxx",
     *    "operation":"QS_REPAIR_REPORT",
     *    "data":[{
     *       "id":"xxx",
     *        "rfid_card_no":"xxx",
     *       "reportTime":"2018-05-07 07:31:00"
     *    }],
     *    "requester":"01",
     *    "requestTime":"2018-05-07 08:00:00"
     * }
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/post_repair_report")
    public ResponseResult<?> PostRepairReport(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:" + jsonObject);
        JSONArray array = jsonObject.getJSONArray("data");
        List<ProofDeviceDTO> proofDeviceDTOList = JSONObject.parseArray(array.toString(), ProofDeviceDTO.class);
        if (CollectionUtils.isEmpty(proofDeviceDTOList))
            return new ResponseResult<List<ProofDeviceDTO>>(ResultStatus.LACK_PARAM);
        String id = proofDeviceDTOList.get(0).getId();
        String rfidCardNo = proofDeviceDTOList.get(0).getRfidCardNo();
        ResultStatus resultStatus = measureRecordService.repairReport(id, rfidCardNo);
        return new ResponseResult<>(resultStatus);
    }

    /**
     * 故障上报
     * {
     *    "requestId":"xxx",
     *    "operation":" QS_FAULTS_REPORT ",
     *    "proof_device_id":"xxx",
     *    "rfid_card_no":"xxx",
     *    "status":"0"
     *    "data":[{
     *       "verification_item":"X",
     *       "verification_content":"XX",
     *       "fault_performance":"XXX",
     *       "emergency_response":"XXXX",
     *       "note":"XXXXX",
     *       "is_qualified":"X"
     *       },{
     *      "verification_item":"X",
     *       "verification_content":"XX",
     *       "fault_performance":"XXX",
     *       "emergency_response":"XXXX",
     *       "note":"XXXXX",
     *       "is_qualified":"X"
     *       }],
     *    "requester":"01",
     *    "requestTime":"2018-05-07 08:00:00"
     * }
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/post_equipment_faults")
    public ResponseResult<?> postEquipmentFaults(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:" + jsonObject);
        JSONArray array = jsonObject.getJSONArray("data");
        List<MeasureRecordDTO> measureRecordDTOs = JSONObject.parseArray(array.toString(), MeasureRecordDTO.class);
        if (CollectionUtils.isEmpty(measureRecordDTOs))
            return new ResponseResult<List<ProofDeviceDTO>>(ResultStatus.LACK_PARAM);
        String id = jsonObject.getString("proof_device_id");
        Integer status = jsonObject.getInteger("status");
        String rfidCardNo = jsonObject.getString("rfid_card_no");
        ResultStatus resultStatus = measureRecordService.postEquipmentFaults(id, status, rfidCardNo, measureRecordDTOs);
        return new ResponseResult<>(resultStatus);
    }
}
