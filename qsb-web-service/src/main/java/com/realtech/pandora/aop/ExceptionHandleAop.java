package com.realtech.pandora.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.realtech.pandora.common.exception.ProjectException;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;

import lombok.extern.slf4j.Slf4j;


/**
 * 统一异常处理
 * @author yangyiwei
 * @date 2018年7月18日
 * @time 上午10:45:01
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandleAop {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseResult<Object> handle(Exception e) {
		ResponseResult<Object> result = new ResponseResult<Object>();
		if (e instanceof ProjectException) { //如果是自定义异常
			ProjectException projectException = (ProjectException) e;
			ResultStatus resultStatus = projectException.getResultStatus();
			if (projectException.getDetailMsg() != null) {
				result.setData(projectException.getDetailMsg());
			}
			log.error("出现了自定义的错误-----！！！！", e);
			return result.setCode(resultStatus);
		}
		log.error("出现了系统未知的错误-----！！！！", e);
		e.printStackTrace();// 未知错误，打印出来
		return result.setCode(ResultStatus.UNKNOW_ERROR);
	}
}
