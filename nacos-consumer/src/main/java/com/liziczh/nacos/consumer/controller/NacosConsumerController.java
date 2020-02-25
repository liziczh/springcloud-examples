package com.liziczh.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhehao.chen
 */
@RestController
@RequestMapping("/consumer/")
public class NacosConsumerController {
	@Autowired
	private RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@GetMapping(value = "get")
	public String consumer() {
		return restTemplate.getForObject("http://nacos-provider/provider/out/123456", String.class);
	}
}
