package com.liziczh.eureka.service.consumer.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "EUREKA-SERVICE-PROVIDER", path = "/provide/")
public interface FeignService {

	@GetMapping(value = "out/{value}")
	String provide(@PathVariable String value);
}
