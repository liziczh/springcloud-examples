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

	@GetMapping(value = "get/{value}")
	public String get(@PathVariable String value){
		return feignService.provide(value);
	}
}
