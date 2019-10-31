package com.nyq.weixin.service.impl;

import org.springframework.web.bind.annotation.RestController;

import com.nyq.entity.AppEntity;
import com.nyq.weixin.service.WeiXinService;

@RestController
public class WeiXinServiceImpl implements WeiXinService {

	public AppEntity getApp() {
		return new AppEntity("qwe","123");
	}
	
}
