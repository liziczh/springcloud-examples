package com.liziczh.service.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provide/")
public class EurekaServiceProviderController {
	@GetMapping(value = "out/{value}")
	public String provide(@PathVariable String value){
		return "EurekaServiceProvider::" + value;
	}
}
