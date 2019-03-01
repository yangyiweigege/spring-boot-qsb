package com.realtech.pandora.dao;

import com.realtech.pandora.domain.DTO.ProofDeviceDTO;
import com.realtech.pandora.domain.Equipment;
import com.realtech.pandora.domain.ProofDevice;
import com.realtech.pandora.common.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 防错装置数据层
 * 
 * @author yangyiwei
 * @date 2018年7月25日
 * @time 上午10:08:08
 */
public interface ProofDeviceMapper extends MyMapper<ProofDevice> {

	/**
	 * 根据条件查询防错记录
	 * 
	 * @param proofDeviceDTO
	 * @return
	 */
	public List<ProofDeviceDTO> queryErrorRecord(ProofDeviceDTO proofDeviceDTO);

	/**
	 * 根据真实设备id对防错装置点进行分组统计
	 * 
	 * @param equipments
	 * @return
	 */
	public List<Map<String, Object>> groupByEquipmentIds(List<String> equipments);

	/**
	 * 根据工厂虚拟id查询设备绑定的所有防错点
	 * 
	 * @param id
	 * @return
	 */
	public List<ProofDevice> findDeviceBindProof(String id);

	/**
	 * 分页统计数量
	 * @param id
	 * @return
	 */
	public int countDeviceBindProof(String id);

	/**
	 * 修改最近上报时间
	 * @param id
	 * @return
	 */
	public int updateReportTime(@Param("reportTime") String reportTime, @Param("id") String id);

	/**
	 * 查询防错清单数据
	 * @return
	 */
	public List<ProofDeviceDTO> exportProofList(@Param("factoryId") String factoryId);

	/**
	 * 分页统计
	 * @param id
	 * @return
	 */
	public int countProofDeviceByPid(String id);

	/**
	 * 根据 proof-map表中的pid 查询对应的防错装置
	 * @param id
	 * @return
	 */
	public List<ProofDevice> findProofDeviceByPid(String id);

	/**
	 * 根据设备id查询防错装置
	 * @param code
	 * @return
	 */
	public List<ProofDevice> findProofDeviceByCode(String code);

	/**
	 * 根据防错装置id联表查询详情
	 * @param id
	 * @return
	 */
	public List<ProofDeviceDTO> findProofDeviceById(String id);
}