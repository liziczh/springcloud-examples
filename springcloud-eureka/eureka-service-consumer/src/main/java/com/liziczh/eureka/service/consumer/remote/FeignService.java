package com.liziczh.eureka.service.consumer.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "EUREKA-SERVICE-PROVIDER", fallback = FeignServiceFallback.class)
public interface FeignService {
	@GetMapping(value = "/provide/hello")
	String hello();
	@GetMapping(value = "/provide/name/{name}")
	String name(@PathVariable String name);
}
