package com.liziczh.eureka.service.consumer.remote;

import org.springframework.stereotype.Component;

@Component
public class ProviderFeignClientFallback implements ProviderFeignClient {
	@Override
	public String provide(String value) {
		return "Out Error";
	}
	@Override
	public String ribbon() {
		return "Port Error";
	}
}
