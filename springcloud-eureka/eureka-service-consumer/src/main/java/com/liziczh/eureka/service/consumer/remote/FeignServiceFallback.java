package com.liziczh.eureka.service.consumer.remote;

import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallback implements FeignService {
	@Override
	public String name(String name) {
		return "Name Error";
	}
	@Override
	public String hello() {
		return "Hello Error";
	}
}
