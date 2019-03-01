package com.realtech.pandora.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.realtech.pandora.common.request.ParameterRequestWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 替换请求中的request 可添加param参数
 * 
 * @author yangyiwei
 * @date 2018年8月6日
 * @time 上午11:18:26
 */
//@WebFilter(filterName = "replaceRequestFilter", urlPatterns = "/*")
@Slf4j
public class ReplaceRequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("采用了自定义的request替换了httpServletRequest......");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(httpServletRequest);
		chain.doFilter(requestWrapper,response);
	}

	@Override
	public void destroy() {

	}

}
