package com.liziczh.nacos.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.config.annotation.NacosValue;

/**
 * @author zhehao.chen
 */
@RestController
@RequestMapping("/consumer/")
public class NacosConsumerController {
	@NacosValue(value = "${lizi.consumerValue}", autoRefreshed = true)
	private String consumerValue;

	@GetMapping(value = "get")
	@ResponseBody
	public String consumer() {
		return "Nacos Consumer Value:" + consumerValue;
	}
}
