package com.realtech.pandora.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.realtech.pandora.common.annotation.ValidatePage;


/**
 * <pre>
 * 功       能:校验参数拦截器 
 * 涉及版本: V2.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2017年10月18日 下午6:38:34
 * Q     Q: 2873824885
 * </pre>
 */
public class ValidatePageInterceptor extends HandlerInterceptorAdapter {

	private final static Logger logger = Logger.getLogger(HandlerInterceptorAdapter.class);

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			ValidatePage validate = method.getAnnotation(ValidatePage.class);
			if (validate != null) {
				logger.info("此方法存在 ValidatePage注解...进行分页参数校验中......");
				String pageNum = request.getParameter("pageNum");
				String pageSize = request.getParameter("pageSize");
				
				if (pageNum == null) {
					ArrayList<String> list = new ArrayList<String>();
					list.add("1");
					getParamters(request).put("pageNum", list);
				}
				if (pageSize == null) {
					ArrayList<String> list = new ArrayList<String>();
					list.add("10");
					getParamters(request).put("pageSize", list);
				}
			}
		}
		return true;
	}


	/**
	 * 通过反射获取 request类中的 map
	 * @param request
	 * @return
	 */
	public Map<String,ArrayList<String>> getParamters(HttpServletRequest request) {
		Field requestField;
		Field parametersParsedField;
		Field coyoteRequestField;
		Field parametersField;
		Field hashTabArrField;
		try {
			Class<?> clazz = Class.forName("org.apache.catalina.connector.RequestFacade");
			requestField = clazz.getDeclaredField("request");
			requestField.setAccessible(true);

			parametersParsedField = requestField.getType().getDeclaredField("parametersParsed");
			parametersParsedField.setAccessible(true);

			coyoteRequestField = requestField.getType().getDeclaredField("coyoteRequest");
			coyoteRequestField.setAccessible(true);

			parametersField = coyoteRequestField.getType().getDeclaredField("parameters");
			parametersField.setAccessible(true);
			
			hashTabArrField = parametersField.getType().getDeclaredField("paramHashValues");
			//hashTabArrField = parametersField.getType().getDeclaredField("paramHashStringArray");
			hashTabArrField.setAccessible(true);
			// 修改parma操作
			Object innerRequest = requestField.get(request);
			parametersParsedField.setBoolean(innerRequest, true);
			Object coyoteRequestObject = coyoteRequestField.get(innerRequest);
			Object parameterObject = parametersField.get(coyoteRequestObject);
			return (Map<String,ArrayList<String>>) hashTabArrField.get(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}