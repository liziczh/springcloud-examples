package com.liziczh.nacos.springboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;

@SpringBootApplication
@EnableNacosDiscovery
public class NacosSpringbootApplication {
	@NacosInjected
	private NamingService namingService;

	@Value("${server.port}")
	private int serverPort;

	@Value("${spring.application.name}")
	private String applicationName;

	@PostConstruct
	public void registerInstance() throws NacosException {
		namingService.registerInstance(applicationName, "47.93.251.190", serverPort);
	}
	public static void main(String[] args) {
		SpringApplication.run(NacosSpringbootApplication.class, args);
	}
}
