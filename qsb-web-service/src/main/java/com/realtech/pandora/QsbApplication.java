package com.realtech.pandora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
@MapperScan("com.realtech.pandora.dao") // 配置tk-mybatis-dao层扫描
@ComponentScan(basePackages = { "com.realtech.pandora" }) // 基本扫包配置
@EnableScheduling // 定时任务配置开启
@EnableAsync // 开启异步任务
// @EnableEurekaClient//服务注册
// @EnableJms // 开启JMS消息服务
@ServletComponentScan // servlete组件扫描
public class QsbApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(QsbApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(QsbApplication.class);
	}

}
