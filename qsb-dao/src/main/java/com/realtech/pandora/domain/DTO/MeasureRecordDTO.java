package com.realtech.pandora.domain.DTO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.realtech.pandora.domain.Person;
import lombok.Data;

import java.util.List;

/**
 * Created by zhuqq
 */
@Data
public class MeasureRecordDTO {

    private String id;//防错措施记录表id

    private String proofDeviceId;//防错装置id
    @Excel(name = "验证项目", orderNum = "0")
    private String verificationItem;//验证项目
    @Excel(name = "验证内容", orderNum = "1")
    private String verificationContent;//验证内容
    @Excel(name = "故障表现", orderNum = "2")
    private String faultPerformance;//故障表现
    @Excel(name = "应急响应", orderNum = "3")
    private String emergencyResponse;//应急响应

    private String reporterPersonUserObjId;//上报对象引用id

    private String fixerPersonUserObjId;//修复对象引用id

    private Integer bach;//批次号

    private Integer dataVersion;//版本号

    private String note;//备注

    @Excel(name = "上报人", orderNum = "4")
    private String reportPersons;//上报人
    @Excel(name = "上报时间", orderNum = "5", databaseFormat = "yyyy-MM-dd HH:mm:ss", isWrap = false, width = 20)
    private String reportTime;//上报时间
    @Excel(name = "修复人", orderNum = "6")
    private String fixPersons;//修复人
    @Excel(name = "修复时间", orderNum = "7", databaseFormat = "yyyy-MM-dd HH:mm:ss", isWrap = false, width = 20)
    private String fixTime;//修复时间

    private Byte Terminal;//是否来自终端

    private Byte isQualified;//是否合格

    private String createAt;//创建时间

    private String createBy;//创建人

    private String updateAt;//修改时间

    private String updateBy;//修改人

    private List<Person> reportPersonList;//上报人集合

    private List<Person> fixPersonList;//修复人集合

    public MeasureRecordDTO(String id, String proofDeviceId, String verificationItem) {
        this.id = id;
        this.proofDeviceId = proofDeviceId;
        this.verificationItem = verificationItem;
    }

    public MeasureRecordDTO() {
    }
}
