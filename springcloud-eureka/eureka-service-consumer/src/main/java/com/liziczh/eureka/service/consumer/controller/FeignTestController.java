package com.liziczh.eureka.service.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liziczh.eureka.service.consumer.remote.ProviderFeignService;

@RestController
@RequestMapping(value = "/feign/")
public class FeignTestController {
	@Autowired
	private ProviderFeignService providerFeignService;

	@GetMapping(value = "get/{value}")
	public String get(@PathVariable String value){
		return providerFeignService.provide(value);
	}

	@GetMapping(value = "port")
	public String port(){
		return providerFeignService.ribbon();
	}
}
