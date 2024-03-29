package com.nyq.api.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	// rest和springcloud 的fegin组件
	@RequestMapping("getOrder")
	public String getOrder() {
		String url = "http://consul-member/getMember";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println("会员服务调用订单服务,result:" + result);
		return result;
	}
	
	// rest和springcloud 的fegin组件
	@RequestMapping("/discoveryClient")
	public List<ServiceInstance> discoveryClient() {
		List<ServiceInstance> instances = discoveryClient.getInstances("zk-member");
		for (ServiceInstance serviceInstance : instances) {
			System.out.println("url:" + serviceInstance.getUri());
		}
		return instances;
	}
}
