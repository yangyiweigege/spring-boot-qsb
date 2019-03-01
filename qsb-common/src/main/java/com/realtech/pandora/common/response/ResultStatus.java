package com.realtech.pandora.common.response;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * <pre>
 * 功       能:返回状态枚举类 
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2018年3月9日 下午1:12:21
 * Q    Q: 2873824885
 * </pre>
 */
public enum ResultStatus {
	
	/**
	 * 请求结果
	 */
	UNKNOW_ERROR("出现未知错误", -1), //系统抛出异常
	SUCCESS("请求成功", 0),  //请求成功
	DEFINE_ERROR("自定义错误", -2), 
	
	/**基本参数校验**/
	LACK_PARAM("缺乏基本参数",  InnerCode.getIncrmentI()),
	
	/**基本枚举类型 插入失败 更新失败 添加失败 未查询到匹配数据**/
	DATA_NOT_FIND("数据没有找到",  InnerCode.getIncrmentI()), 
	UPDATE_FAIL("未更新到匹配记录", InnerCode.getIncrmentI()),
	INSERT_FAIL("插入数据失败",  InnerCode.getIncrmentI()),
	DELETE_FAIL("删除数据失败",  InnerCode.getIncrmentI()),
	TRANSACTION_FAIL("多表操作失败", InnerCode.getIncrmentI()),

	/**工厂模块枚举类型**/
	FACTORY_TYPE_NOT_MATCH("地点类型不符", InnerCode.getIncrmentI()),
	VIRTUAL_DEVICE_NOT_MATCH("该工厂节点并不存在真实设备", InnerCode.getIncrmentI()),

	/**责任人模块**/
	PERSON_IS_ILLEGAL("上传的责任人中存在非法用户", InnerCode.getIncrmentI()),
	QUERY_PERSON_FAIL("卡号未匹配到人员", InnerCode.getIncrmentI()),

	/**防错装置模块**/
	PROOF_DEVICE_EXIST_MEASURE_RECORD("防错装置下存在防错记录", InnerCode.getIncrmentI()),
	
	/**防错报警装置模块**/
	ALERT_CAN_NOT_GT_THREE("防错报警层数不能大于三层", InnerCode.getIncrmentI()),
	/*设备*/
	EQUIPMENT_FIND_ERROR("未查询到设备或查询到多条设备", InnerCode.getIncrmentI()),
	


	/*终端数据*/
	TERMINAL_RECORD("终端数据不能删除", InnerCode.getIncrmentI()),

	/*EXCEL数据导出*/
	EXCEL_EXPORT_ERROR("导出数据不能为空", InnerCode.getIncrmentI()),

	/*上传文件错误*/
	FILE_UPLOAD_ERROR("上传文件不能为空", InnerCode.getIncrmentI()),
	FILE_UPLOAD_TYPE_ERROR("必须是JPEG/PNG格式", InnerCode.getIncrmentI()),
	
	
	;
	/**
	 * 消息
	 */
	private String message;

	/**
	 * 状态码
	 */
	private Integer code;
	
	
	static class InnerCode { //内部定义状态码自增
		public static AtomicInteger i = new AtomicInteger(1);
		
		/**
		 * juc下的 atomic cas自增长 i 
		 * @return
		 */
		public static int getIncrmentI() {
			return i.getAndIncrement();
		}
	}
	

	ResultStatus(String message, Integer code) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static void showAllType() {
		for (ResultStatus e : ResultStatus.values()) {
			System.out.println(e.getMessage() + "  " + e.getCode());
		}
	}

	
	/*  public static void main(String[] args) {
		for (ResultStatus resultStatus : ResultStatus.values())  {
			System.out.println(resultStatus.getCode() + " 消息 " + resultStatus.message );
		}
	  }*/
	 
}
