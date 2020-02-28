package com.liziczh.service.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ribbon/")
public class RibbonController {
	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String port;
	/**
	 * Ribbon负载均衡测试接口
	 * @return
	 */
	@GetMapping(value = "port")
	public String ribbon(){
		return appName + "::" + port;
	}
}
