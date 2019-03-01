package com.realtech.pandora.domain.DTO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.realtech.pandora.domain.Person;
import com.realtech.pandora.domain.SystemResource;
import lombok.Data;

import java.util.List;

import org.h2.util.New;

/**
 *出错记录列表
 * Created by zhuqq
 */
@Data
public class ProofDeviceDTO {
	@JsonInclude(Include.NON_NULL)
    private String id;//防错装置id
	@Excel(name = "厂区", orderNum = "0", isWrap = false, width = 15)
    private String factoryName;//厂区
	@JsonInclude(Include.NON_NULL)
    private String factoryLocationId;//工厂地点id
	@JsonInclude(Include.NON_NULL)
    private String equipmentId;//设备id
	@Excel(name = "设备", orderNum = "2", isWrap = false, width = 20)
	@JsonInclude(Include.NON_NULL)
    private String equipmentName;//设备名
	@Excel(name = "识别号", orderNum = "3", isWrap = false, width = 15)
    private String code;//识别号
	@Excel(name = "工序", orderNum = "4", isWrap = false, width = 15)
    private String process;//工序
	@Excel(name = "防错装置名称", orderNum = "5", isWrap = false, width = 20)
    private String name;//防错装置名称
    private Byte proofType;//防错类型
    private Byte isItem;//是否点检项
	@JsonInclude(Include.NON_NULL)
    private String personName;//责任人名字(前端传入)
	@Excel(name = "责任人", orderNum = "8", isWrap = false, width = 20)
    private String persons;//责任人(后台返回)
	@JsonInclude(Include.NON_NULL)
    private Integer verificationTime;//验证频率时间
	@JsonInclude(Include.NON_NULL)
    private Byte verificationTimeUnit;//验证频率单位
	@JsonInclude(Include.NON_NULL)
    private Integer verificationTimeNum;//验证频率数
    private Byte status;//当前状态

    private String reportTime;//最近上报时间
	@JsonInclude(Include.NON_NULL)
    private String startTime;//起始时间
	@JsonInclude(Include.NON_NULL)
    private String endTime;//结束时间
    private String location;//地点
    private String installationTime;//装置时间
    private String updateAt;//最后修改时间
	@Excel(name = "失效模式", orderNum = "9")
    private String failureMode;//失效模式
	@JsonInclude(Include.NON_NULL)
    private Integer maintenanceTime;//保养频率时间
	@JsonInclude(Include.NON_NULL)
    private Byte maintenanceTimeUnit;//保养频率单位
	@JsonInclude(Include.NON_NULL)
    private Integer maintenanceTimeNum;//保养频率数
    private Byte verificationMethod;//验证方法
    private Byte proofCategory;//防错类别
    private String purpose;//防错装置目的
    private String proofDeviceDesc;//防错装置描述
	@JsonInclude(Include.NON_NULL)
    private Byte terminal;//来源
	@JsonInclude(Include.NON_NULL)
	private List<Person> personList;//人员id,人员
	@JsonInclude(Include.NON_NULL)
	private String statusStr;//当前状态
	@Excel(name = "验证频率", orderNum = "11")
	@JsonInclude(Include.NON_NULL)
	private String  verificationNumStr;//验证频率
	@Excel(name = "保养频率", orderNum = "10")
	@JsonInclude(Include.NON_NULL)
	private String maintenanceNumStr;//保养频率
	@Excel(name = "车间", orderNum = "1", isWrap = false, width = 15)
	@JsonInclude(Include.NON_NULL)
	private String workshop;//车间
	@Excel(name = "验证方法", orderNum = "12")
	@JsonInclude(Include.NON_NULL)
	private String verificationMethodStr;//验证方法
	@Excel(name = "是否点检项", orderNum = "7")
	@JsonInclude(Include.NON_NULL)
	private String isItemStr;//是否点检项
	@Excel(name = "防错类型", orderNum = "6")
	@JsonInclude(Include.NON_NULL)
	private String proofTypeStr;//防错类型
	@JsonInclude(Include.NON_NULL)
	private List<SystemResource> systemResourceList;//防错装置图片

	private String imageName; //防错装置单张图片
	@JsonInclude(Include.NON_NULL)
	private String creator;//终端需要
	@JsonInclude(Include.NON_NULL)
	private String rfidCardNo;
	@JsonInclude(Include.NON_NULL)
	private String factoryId;//工厂id

}
