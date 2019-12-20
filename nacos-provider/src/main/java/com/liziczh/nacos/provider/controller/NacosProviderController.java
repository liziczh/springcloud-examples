package com.liziczh.nacos.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.config.annotation.NacosValue;

/**
 * @author zhehao.chen
 */
@RestController
@RequestMapping("/provider/")
public class NacosProviderController {
	@NacosValue(value = "${lizi.providerValue}", autoRefreshed = true)
	private String providerValue;

	@GetMapping(value = "get")
	@ResponseBody
	public String provider() {
		return "Nacos Provider Value:" + providerValue;
	}
}
