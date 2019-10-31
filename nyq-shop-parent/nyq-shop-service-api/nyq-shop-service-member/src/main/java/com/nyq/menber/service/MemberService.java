package com.nyq.menber.service;

import org.springframework.web.bind.annotation.GetMapping;

import com.nyq.entity.AppEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="会员服务接口")
public interface MemberService {
	
	@ApiOperation(value="会员服务调用微信服务")
	@GetMapping("memberToWeiXin")
	public AppEntity memberToWeiXin();
}
