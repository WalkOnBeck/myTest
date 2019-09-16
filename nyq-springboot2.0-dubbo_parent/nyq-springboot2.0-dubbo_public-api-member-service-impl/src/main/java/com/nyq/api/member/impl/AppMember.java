package com.nyq.api.member.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableDubbo
@SpringBootApplication
public class AppMember {
	public static void main(String[] args) {
		SpringApplication.run(AppMember.class, args);
	}
}
