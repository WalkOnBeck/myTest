package com.nyq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nyq.api.member.IMemberService;

@RestController
public class OrderConreoller {
	@Reference
	private IMemberService iMemberService;
	
	@RequestMapping("/orderToMember")
	public String orderToMember() {
		return iMemberService.getUser();
	}
}
