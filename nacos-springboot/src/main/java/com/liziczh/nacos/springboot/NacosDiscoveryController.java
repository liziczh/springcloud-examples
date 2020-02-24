package com.liziczh.nacos.springboot;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

@RestController
@RequestMapping(value = "discovery")
public class NacosDiscoveryController {
	@NacosInjected
	private NamingService namingService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Instance> getInstance(@RequestParam String serviceName) throws NacosException {
		return namingService.getAllInstances(serviceName);
	}
}
