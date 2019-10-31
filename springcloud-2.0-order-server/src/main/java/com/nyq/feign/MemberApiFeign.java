package com.nyq.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="app-nyq-member")
public interface MemberApiFeign {
	
	@RequestMapping("/getMember")
	public String getMember();
}
