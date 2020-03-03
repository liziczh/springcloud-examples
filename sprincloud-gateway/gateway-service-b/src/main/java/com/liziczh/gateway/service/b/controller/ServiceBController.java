package com.liziczh.gateway.service.b.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/b/")
public class ServiceBController {

	@Value("spring.application.name")
	private String appName;

	@Value("server.port")
	private String port;

	public String hello(){
		return "Hello! My Application Name is " + appName + ", My Port is " + port;
	}
}
