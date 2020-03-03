package com.liziczh.eureka.service.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liziczh.eureka.service.consumer.remote.FeignService;

@RestController
@RequestMapping(value = "/feign/")
public class FeignController {
	@Autowired
	private FeignService feignService;

	/**
	 * Feign服务调用测试
	 * @param name
	 * @return
	 */
	@GetMapping(value = "name/{name}")
	public String name(@PathVariable String name) {
		return feignService.name(name);
	}
	/**
	 * Ribbon负载均衡测试
	 * @return
	 */
	@GetMapping(value = "hello")
	public String hello() {
		return feignService.hello();
	}
}
