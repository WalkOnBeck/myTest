package com.nyq.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyq.rabbitmq.simpl.HelloSender;
import com.nyq.rabbitmq.work.HelloWorkSender;

@RestController
public class MqController {
	@Autowired
    private HelloSender helloSender;
	@Autowired
    private HelloWorkSender helloWorkSender;
	
	@RequestMapping("/mq")
	public void mq() {
		helloSender.send();
	}
	
	@RequestMapping("/workmq")
	public void workmq() {
		helloWorkSender.sendWork();
	}
}
