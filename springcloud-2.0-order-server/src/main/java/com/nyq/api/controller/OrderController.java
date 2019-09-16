package com.nyq.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;
	
	// rest和springcloud 的fegin组件
	@RequestMapping("getOrder")
	public String getOrder() {
		String url = "http://app-nyq-member/getMember";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println("会员服务调用订单服务,result:" + result);
		return result;
	}
}
