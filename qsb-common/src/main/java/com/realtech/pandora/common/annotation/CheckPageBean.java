package com.realtech.pandora.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验分页尺寸
 * @author yangyiwei
 * @date 2018年8月8日
 * @time 下午2:40:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPageBean {
	/** 默认无属性 **/
	public String value() default "";

	/** 校验当前页属性 默认pageNum **/
	public String currentPage() default "pageNum";

	/** 当前分页尺寸 默认pageSize参数 **/
	public String pageSize() default "pageSize";
}
