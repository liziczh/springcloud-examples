package com.liziczh.gateway.service.a.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {
	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String port;

	@GetMapping(value = "hello")
	public String hello() {
		return "Hello! My Application Name is " + appName + ", My Port is " + port;
	}
}
