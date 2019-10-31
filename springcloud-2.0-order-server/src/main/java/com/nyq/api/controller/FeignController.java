package com.nyq.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyq.feign.MemberApiFeign;

@RestController
public class FeignController {
	
	@Autowired
	private MemberApiFeign memberApiFeign;
	
	@RequestMapping("/getFeignMember")
	public String getFeignMember() {
		return memberApiFeign.getMember();
	}
}
