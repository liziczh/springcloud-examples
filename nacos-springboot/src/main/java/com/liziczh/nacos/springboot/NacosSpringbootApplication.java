package com.liziczh.nacos.springboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;

@SpringBootApplication
public class NacosSpringbootApplication {
	@NacosInjected
	private NamingService namingService;

	@Value("${server.port}")
	private int serverPort;

	@Value("${spring.application.name}")
	private String applicationName;

	@PostConstruct
	public void registerInstance() throws NacosException {
		namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
	}
	public static void main(String[] args) {
		SpringApplication.run(NacosSpringbootApplication.class, args);
	}
}
