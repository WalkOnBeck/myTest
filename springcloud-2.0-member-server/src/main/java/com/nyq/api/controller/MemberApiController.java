package com.nyq.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

	@Value("${server.port}")
	private String serverPort;
	
	@RequestMapping("/getMember")
	public String getMember() {
		return "Hello springcloud!" + serverPort;
	}
}
