package com.domor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.domor.common.AuthInterceptor;

@Configuration
@SuppressWarnings("deprecation")
public class AuthConfiguration extends WebMvcConfigurerAdapter {

	//此处用于解决拦截器无法注入service的问题
	@Bean
	public AuthInterceptor authInterceptor(){
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    // addPathPatterns 用于添加拦截规则
	    // excludePathPatterns 用户排除拦截
	    registry.addInterceptor(authInterceptor())
	    		.addPathPatterns("/**")
	    		.excludePathPatterns("/toLogin","/login","/noAuth","/my/**");
	}
	
}
