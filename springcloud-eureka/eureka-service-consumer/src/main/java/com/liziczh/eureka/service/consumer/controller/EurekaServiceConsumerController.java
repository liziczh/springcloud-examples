package com.liziczh.eureka.service.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/consume/")
public class EurekaServiceConsumerController {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 服务远程调用测试
	 * @param value
	 * @return
	 */
	@GetMapping(value = "get/{value}")
	public String get(@PathVariable String value){
		String url = "http://eureka-service-provider:8081/provide/out/"+value;
		return restTemplate.getForObject(url, String.class);
	}
}
