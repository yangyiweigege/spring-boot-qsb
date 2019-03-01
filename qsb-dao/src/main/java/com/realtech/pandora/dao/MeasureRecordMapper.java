package com.realtech.pandora.dao;

import com.realtech.pandora.common.mapper.MyMapper;
import com.realtech.pandora.domain.DTO.MeasureRecordDTO;
import com.realtech.pandora.domain.MeasureRecord;
import com.realtech.pandora.domain.MeasureTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MeasureRecordMapper extends MyMapper<MeasureRecord> {

    /**
     * 查询最大批次号
     * @Param id  装置id
     * @return
     */
    public Integer queryMaxBach(String id);

    /**
     * 查询防错措施记录列表
     * @param id
     * @return
     */
    public List<MeasureRecordDTO> queryMeasureRecordList(String id);

    /**
     * 根据防错装置id查询最近上报时间
     * @param proofDeviceId
     * @return
     */
    public String queryReportTime(String proofDeviceId);

    /**
     * 根据批次号和防错装置id查询防错措施记录
     * @param id
     * @param bach
     * @return
     */
    public List<MeasureRecord> findRecordByProofDeviceIdWithBach(@Param("id")String id, @Param("bach")Integer bach);

    /**
     * 修改防错措施记录表
     * @return
     */
    public int updateMeasureRecord(MeasureRecord measureRecord);

    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBach(List<MeasureRecordDTO> list);

    /**
     * 批量修改防错措施记录
     * @param list
     * @return
     */
    public int updateBach(List<MeasureRecordDTO> list);
}