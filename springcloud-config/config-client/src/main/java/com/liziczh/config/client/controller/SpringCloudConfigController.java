package com.liziczh.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/")
@RefreshScope
public class SpringCloudConfigController {
	@Value(value = "${db.username:admin}")
	private String username;
	@GetMapping(value = "testConfig")
	public String testConfig() {
		return "username:" + username;
	}
}
