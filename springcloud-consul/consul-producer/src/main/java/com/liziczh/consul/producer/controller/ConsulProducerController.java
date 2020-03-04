package com.liziczh.consul.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produce/")
public class ConsulProducerController {
	@GetMapping("name/{name}")
	public String name(@PathVariable String name){
		return "My Name is " + name;
	}
}
