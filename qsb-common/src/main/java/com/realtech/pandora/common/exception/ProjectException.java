package com.realtech.pandora.common.exception;

import com.realtech.pandora.common.response.ResultStatus;

/**
 * 自定义项目中出现的异常
 * 
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 上午11:01:33
 */
public class ProjectException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 错误状态
	 */
	private Integer code;

	/**
	 * 状态码
	 */
	private ResultStatus resultStatus;

	/**
	 * 具体原因
	 */
	private String detailMsg;

	public ProjectException(String message) {
		super(message);
		this.code = ResultStatus.UNKNOW_ERROR.getCode();
		this.detailMsg = message;
	}

	public ProjectException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public ProjectException(ResultStatus resultStatus) {
		super(resultStatus.getMessage());
		this.code = resultStatus.getCode();
		this.resultStatus = resultStatus;
	}

	public ProjectException(ResultStatus resultStatus, String detailMsg) {
		super(resultStatus.getMessage());
		this.code = resultStatus.getCode();
		this.resultStatus = resultStatus;
		this.detailMsg = detailMsg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getDetailMsg() {
		return detailMsg;
	}

	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}

}
