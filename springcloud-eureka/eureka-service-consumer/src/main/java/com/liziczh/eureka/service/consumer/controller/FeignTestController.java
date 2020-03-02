package com.liziczh.eureka.service.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liziczh.eureka.service.consumer.remote.ProviderFeignClient;

@RestController
@RequestMapping(value = "/feign/")
public class FeignTestController {
	@Autowired
	private ProviderFeignClient providerFeignClient;
	/**
	 * Feign服务调用测试
	 * @param value
	 * @return
	 */
	@GetMapping(value = "get/{value}")
	public String get(@PathVariable String value){
		return providerFeignClient.provide(value);
	}
	/**
	 * Ribbon负载均衡测试
	 * @return
	 */
	@GetMapping(value = "port")
	public String port(){
		return providerFeignClient.ribbon();
	}
}
