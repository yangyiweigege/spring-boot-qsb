package com.realtech.pandora.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhuqq
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Value("${upload.localtion.windows}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这是系统默认配置
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");

        //这是添加的配置，表示将/picture/..映射到F:/photos/目录
        registry.addResourceHandler("/picture/**").addResourceLocations("file:" + path);
        super.addResourceHandlers(registry);
    }
}
