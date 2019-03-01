package com.realtech.pandora.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.realtech.pandora.interceptor.CheckPageBeanInterceptor;
import com.realtech.pandora.interceptor.RequestInterceptor;
import com.realtech.pandora.interceptor.ValidateAttributeInterceptor;
import com.realtech.pandora.interceptor.ValidateJSONInterceptor;
import com.realtech.pandora.interceptor.ValidatePageInterceptor;




/**
 * <pre>
 * 功       能: spring-boot 拦截器
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2018年4月2日 下午9:22:52
 * Q    Q: 2873824885
 * </pre>
 */
@Configuration
public class WebMvcInterceptor extends WebMvcConfigurerAdapter {

	@Autowired
	private RequestInterceptor requestInterceptor;
	
	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(new OneInterceptor()).addPathPatterns("/**");
		//registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**");//注册session校验
		/*registry.addInterceptor(requestInterceptor).addPathPatterns("/**").excludePathPatterns("/system/resource/**")
																															  //排出终端需要的接口
																															  .excludePathPatterns("/get_pv_order")
																															  .excludePathPatterns("/get_pv_detail")
																															  .excludePathPatterns("/get_pv_template")
																															  .excludePathPatterns("/post_repair_report")
																															  .excludePathPatterns("/post_equipment_faults");*/
		//注册远程服务器拦截
		registry.addInterceptor(new ValidatePageInterceptor()).addPathPatterns("/**"); //注册分页
		registry.addInterceptor(new ValidateAttributeInterceptor()).addPathPatterns("/**"); //注册属性校验
		registry.addInterceptor(new ValidateJSONInterceptor()).addPathPatterns("/**");
	//	registry.addInterceptor(new CheckPageBeanInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);//注册该拦截器
	}
	
	
	
}
