package com.realtech.pandora.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.realtech.pandora.common.annotation.CheckPageBean;
import com.realtech.pandora.common.annotation.ValidatePage;
import com.realtech.pandora.common.request.ParameterRequestWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 对存在pageBean注解的接口 进行分页参数判断以及添加
 * 
 * @author yangyiwei
 * @date 2018年8月8日
 * @time 下午2:43:09
 */
@Slf4j
public class CheckPageBeanInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			CheckPageBean checkPageBean = method.getAnnotation(CheckPageBean.class);
			if (checkPageBean != null) {
				log.info("此方法存在 CheckPageBean注解...进行分页参数校验中......");

				String pageNum = request.getParameter(checkPageBean.currentPage());
				String pageSize = request.getParameter(checkPageBean.pageSize());
				if (pageNum == null) {
					ParameterRequestWrapper requestWrapper = (ParameterRequestWrapper) request;
					requestWrapper.addParameter(checkPageBean.currentPage(), "1");
				}
				if (pageSize == null) {
					ParameterRequestWrapper requestWrapper = (ParameterRequestWrapper) request;
					requestWrapper.addParameter(checkPageBean.pageSize(), "15");
				}
			}
		}
		return true;
	}
}
