package com.liziczh.service.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provide/")
public class EurekaServiceProviderController {
	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String port;
	/**
	 * Ribbon负载均衡测试接口
	 * @return
	 */
	@GetMapping(value = "hello")
	public String hello(){
		return "Hello! I'm " + appName + ", My port is " + port;
	}
	/**
	 * 远程服务调用接口
	 * @param name
	 * @return
	 */
	@GetMapping(value = "name/{name}")
	public String name(@PathVariable String name){
		return "Hello! My name is " + name;
	}
}
