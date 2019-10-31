package com.nyq.member.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.nyq.weixin.service.WeiXinService;

@FeignClient("app-nyq-weixin")
public interface WeiXinServiceFeign extends WeiXinService {
	
}
