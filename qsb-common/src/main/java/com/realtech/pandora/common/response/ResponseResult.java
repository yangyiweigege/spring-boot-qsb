package com.realtech.pandora.common.response;

/**
 * <pre>
 * 功       能:用于分析返回结果 http
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2018年3月9日 下午1:01:09
 * Q    Q: 2873824885
 * </pre>
 */
public class ResponseResult<T> {

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 返回消息
	 */
	private String message;

	/**
	 * 具体内容
	 */
	private T data;

	/**
	 * 设置状态码的构造函数，无返回对象
	 * 
	 * @param resultStatus
	 */
	public ResponseResult(ResultStatus resultStatus) {
		this.code = resultStatus.getCode();
		this.message = resultStatus.getMessage();
	}

	/**
	 * 设置状态码的构造函数，返回对象
	 * 
	 * @param resultStatus
	 */
	public ResponseResult(ResultStatus resultStatus, T data) {
		this.code = resultStatus.getCode();
		this.message = resultStatus.getMessage();
		this.data = data;
	}

	/**
	 * 空构造
	 */
	public ResponseResult() {

	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public ResponseResult<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public ResponseResult<T> setData(T data) {
		this.data = data;
		return this;
	}

	public ResponseResult<T> setCode(ResultStatus resultStatus) {
		this.code = resultStatus.getCode();
		this.message = resultStatus.getMessage();
		return this;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
