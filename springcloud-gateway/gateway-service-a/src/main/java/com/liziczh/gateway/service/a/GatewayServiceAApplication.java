package com.liziczh.gateway.service.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServiceAApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceAApplication.class, args);
	}
}
