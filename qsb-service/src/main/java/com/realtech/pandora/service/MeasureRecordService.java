package com.realtech.pandora.service;

import java.util.List;

import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.LoginUserInfo;
import com.realtech.pandora.domain.DTO.MeasureRecordDTO;
import com.realtech.pandora.domain.MeasureRecord;
import com.realtech.pandora.domain.MeasureTemplate;
import com.realtech.pandora.domain.page.PageBean;

/**
 * 防错措施记录业务层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:15:29
 */
public interface MeasureRecordService {

	/**
	 * 返回所有犯错记录
	 * @return
	 */
	public List<MeasureRecord> findAll();

	/**
	 * 添加防错措施记录
	 * @param measureRecordDTOList
	 * @param proofDeviceId
	 * @param status
	 * @return
	 */
	public ResultStatus addMeasureRecord(List<MeasureRecordDTO> measureRecordDTOList, String proofDeviceId, Integer status, LoginUserInfo loginUserInfo);

	/**
	 * 删除防错措施记录
	 * @param id 防错措施id
	 * @return
	 */
	public ResultStatus delMeasureRecord(String id);

	/**
	 * 查询防错措施记录表
	 * @param proofDeviceId		防错装置id
	 * @param page
	 * @return
	 */
	public List<MeasureRecordDTO> queryMeasureRecord(String proofDeviceId, Integer status, PageBean page);

	/**
	 * 根据批次号和防错装置id查询防错措施记录
	 * @param id
	 * @return
	 */
	public List<MeasureRecord> findRecordByProofDeviceIdWithBach(String id);

	/**
	 * 修复完成上报
	 * @param id
	 * @param rfidCardNo
	 */
	public ResultStatus repairReport(String id, String rfidCardNo);

	/**
	 * 故障上报
	 * @param id
	 * @param status
	 * @param rfidCardNo
	 * @param measureRecordDTOs
	 * @return
	 */
	public ResultStatus postEquipmentFaults(String id, Integer status, String rfidCardNo, List<MeasureRecordDTO> measureRecordDTOs);
}
