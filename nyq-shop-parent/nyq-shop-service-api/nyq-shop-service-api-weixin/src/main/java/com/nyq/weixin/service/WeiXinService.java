package com.nyq.weixin.service;

import org.springframework.web.bind.annotation.GetMapping;

import com.nyq.entity.AppEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="微信服务接口")
public interface WeiXinService {
	/**
	 * 获取应用
	 * @return
	 */
	@ApiOperation(value="获取引用服务")
	@GetMapping("getApp")
	public AppEntity getApp();
}
