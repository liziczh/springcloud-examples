package com.liziczh.gateway.service.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServiceBApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceBApplication.class, args);
	}
}
