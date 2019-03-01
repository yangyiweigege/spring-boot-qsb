package com.realtech.pandora.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 功       能:注解校验page参数 
 * 涉及版本: V2.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2017年10月18日 下午6:27:36
 * Q     Q: 2873824885
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePage {
    /**
     * The value may indicate to validate data
     * @return the suggested field, if any
     */
    public String value() default "";

}
