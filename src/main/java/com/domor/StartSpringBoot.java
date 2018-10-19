package com.domor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

//启用定时任务
//@EnableScheduling
@EnableCaching
@SpringBootApplication//spring boot自带的注解具有扫描主类所在的包的子包的bean的功能。
public class StartSpringBoot extends SpringBootServletInitializer {
	//重写configure方法，返回build.resource(启动类的class对象)，部署在tomcat中需要更改的地方
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StartSpringBoot.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StartSpringBoot.class, args);
	}
}

