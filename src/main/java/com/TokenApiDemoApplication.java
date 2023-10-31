package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan(value = "com.tokenApi.mapper")
@ComponentScan(basePackages = {"com.api.pojo", "com.api.service", "com.api.controller", "com.api.interceptor", "com.api.util", "com.api.config"})
public class TokenApiDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TokenApiDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TokenApiDemoApplication.class);
	}
}
