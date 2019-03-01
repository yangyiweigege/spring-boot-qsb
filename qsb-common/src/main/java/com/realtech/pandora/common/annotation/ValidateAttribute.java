package com.realtech.pandora.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 校验属性不为空字段
 * 
 * @author yangyiwei
 * @date 2018年8月1日
 * @time 下午4:13:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAttribute {
	public String[] attributes();
}
