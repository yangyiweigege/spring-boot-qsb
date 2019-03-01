package com.realtech.pandora.service;

import java.util.List;

import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.domain.DTO.ProofAlertSettingDTO;

/**
 * 防错装置报警配置业务逻辑层
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 下午4:57:31
 */
public interface ProofAlertSettingService {
	/**
	 * 添加防错报警
	 * @param proofAlertSettings
	 * @return
	 */
	public ResultStatus addAlertSettings(List<ProofAlertSettingDTO> proofAlertSettings);

	/**
	 * 根据防错装置id查询报警列表
	 * @param id
	 * @return
	 */
	public List<ProofAlertSettingDTO> findListByProofId(String id);

	/**
	 * 编辑报警
	 * @param proofAlertSettings
	 * @return
	 */
	public ResultStatus editAlertSettings(List<ProofAlertSettingDTO> proofAlertSettings);
	
	/**
	 * 删除报警错装置根据防错装置id
	 * @param proofAlertSettings
	 * @return
	 */
	public ResultStatus delAlertSettings(String proofDeviceId);
}
