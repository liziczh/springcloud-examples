package com.liziczh.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.liziczh.service.provider.config.RibbonConfig;

@EnableEurekaClient
@RibbonClient(name = "RibbonClient", configuration = RibbonConfig.class)
@SpringBootApplication
public class EurekaServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceProviderApplication.class, args);
	}
}
