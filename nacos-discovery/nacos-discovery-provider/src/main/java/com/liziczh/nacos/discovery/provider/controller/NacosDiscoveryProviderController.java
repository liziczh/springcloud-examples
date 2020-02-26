package com.liziczh.nacos.discovery.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.config.annotation.NacosValue;

/**
 * @author zhehao.chen
 */
@RestController
@RequestMapping("/provider/")
public class NacosDiscoveryProviderController {
	@GetMapping(value = "out/{value}")
	public String provider(@PathVariable String value) {
		return "Nacos Provider Value:" + value;
	}
}
