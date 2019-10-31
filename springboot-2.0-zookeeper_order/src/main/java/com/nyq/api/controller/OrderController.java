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
		String url = "http://zk-member/getMember";
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
	
	@RequestMapping("/getSession")
	public Object getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("aa", "aa");
		Enumeration<String> attrs = session.getAttributeNames();
		// 遍历attrs中的 
		while(attrs.hasMoreElements()){ 
			// 获取session键值 
			String name1 = attrs.nextElement().toString(); 
			// 根据键值取session中的值 
			Object vakue = session.getAttribute(name1); 
			// 打印结果 
			System.out.println("------" + name1 + ":" + vakue +"--------\n");}
		return null;
	}

}
