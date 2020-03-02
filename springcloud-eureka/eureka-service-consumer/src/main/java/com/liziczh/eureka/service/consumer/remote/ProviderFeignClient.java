package com.liziczh.eureka.service.consumer.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "EUREKA-SERVICE-PROVIDER", fallback = ProviderFeignClientFallback.class)
public interface ProviderFeignClient {

	@GetMapping(value = "/provide/out/{value}")
	String provide(@PathVariable String value);

	@GetMapping(value = "/ribbon/port")
	String ribbon();
}
