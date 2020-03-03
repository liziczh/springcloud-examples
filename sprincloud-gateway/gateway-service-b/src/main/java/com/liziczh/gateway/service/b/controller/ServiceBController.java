package com.liziczh.gateway.service.b.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {
	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String port;

	@GetMapping(value = "hello")
	public String hello() {
		return "Hello! My Application Name is " + appName + ", My Port is " + port;
	}
}
