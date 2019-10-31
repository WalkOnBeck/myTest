package com.nyq.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nyq.entity.AppEntity;
import com.nyq.member.feign.WeiXinServiceFeign;
import com.nyq.menber.service.MemberService;

@RestController
public class MemberServiceImpl implements MemberService {
	@Autowired
	private WeiXinServiceFeign weiXinServiceFeign;

	@Override
	public AppEntity memberToWeiXin() {
		// TODO Auto-generated method stub
		return weiXinServiceFeign.getApp();
	}

	
}
