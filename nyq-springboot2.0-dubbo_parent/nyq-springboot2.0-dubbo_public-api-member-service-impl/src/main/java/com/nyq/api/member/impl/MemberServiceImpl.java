package com.nyq.api.member.impl;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.nyq.api.member.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService{

	@Value("${dubbo.protocol.port}")
	private String dubboPort;
	// dubbo服务发布 采用dubbo注解方式 使用Dubbo 提供@Service注解方式发布
	@Override
	public String getUser() {
		return "订单服务调用会员服务...dubbo端口号" + dubboPort;
	}

}
