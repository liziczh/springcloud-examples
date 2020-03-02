#### Nacos 注册中心

引入Maven依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <version>0.9.0.RELEASE</version>
</dependency>
```

##### 服务提供者

Nacos-Provider配置文件： `bootstrap.yml` 或  `bootstrap.properties`。

```yml
server:
  port: 8082
  servlet:
    context-path: /provider
spring:
  application:
    name: nacos-provider
  cloud:
    nacos:
      # 注册中心
      discovery:
        server-addr: 47.93.251.190:8848 # nacos-server地址和端口
```

SpringBootApplication启动类新增注解：`@EnableDiscoveryClient`。

```java
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryProviderApplication.class, args);
	}
}
```

服务提供接口：

```java
@RestController
@RequestMapping(value = "/provide/")
public class NacosDiscoveryProviderController {
	@GetMapping(value = "out/{value}")
	public String provider(@PathVariable String value) {
		return "Nacos Provider Out::" + value;
	}
}
```

##### 服务消费者

Nacos-Consumer配置文件： `bootstrap.yml` 或  `bootstrap.properties`。

```yaml
server:
  port: 8083
  servlet:
    context-path: /consumer
spring:
  application:
    name: nacos-consumer
  cloud:
    nacos:
      # 注册中心
      discovery:
        server-addr: 47.93.251.190:8848 # nacos-server地址和端口
```

SpringBootApplication启动类新增注解：`@EnableDiscoveryClient`。

```java
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryConsumerApplication.class, args);
	}
}
```

消费者远程调用服务：

```java
@RestController
@RequestMapping(value = "/consume/")
public class NacosDiscoveryConsumerController {
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/get/{value}")
	public String consumer(@PathVariable String value) {
		String url = "http://nacos-provider/provider/provide/out/"+value;
		String callServiceResult = restTemplate.getForEntity(url, String.class).getBody();
		return callServiceResult;
	}
}
```

##### 服务注册策略

Nacos 服务注册策略：每5s向nacos-server发送一次心跳（携带服务名、服务IP和端口等）， 同时nacos-server会向client发起健康检查，支持tcp/http检查，如果15s内无心跳且健康检查失败，则认为实例不健康。如果30秒内健康检查失败则剔除实例。 
