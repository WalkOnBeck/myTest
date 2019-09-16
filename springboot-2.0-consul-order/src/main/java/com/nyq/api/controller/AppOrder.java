package com.nyq.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class AppOrder {

	public static void main(String[] args) {
		SpringApplication.run(AppOrder.class, args);
		
		// RestTemplate必须要用@LoadBalanced就能让这个RestTemplate在请求时拥有 ribbon客户端负载均衡的能力！
	}
	
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
