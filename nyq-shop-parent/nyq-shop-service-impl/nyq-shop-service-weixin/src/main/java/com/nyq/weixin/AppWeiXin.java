package com.nyq.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.spring4all.swagger.EnableSwagger2Doc;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
public class AppWeiXin {
	public static void main(String[] args) {
		SpringApplication.run(AppWeiXin.class, args);
	}
}
