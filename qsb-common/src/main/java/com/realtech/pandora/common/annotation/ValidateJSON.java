package com.realtech.pandora.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验application/json请求中 json的数据
 * @author yangyiwei
 * @date 2018年8月6日
 * @time 上午10:35:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateJSON {
	
	public String[] attributes();
}
