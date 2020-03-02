## Eureka 服务注册

Eureka，服务中心 / 注册中心，管理各种服务功能包括服务的注册、发现、熔断、负载、降级等。

### EurekaServer

添加Maven依赖

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		<version>2.2.1.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
		<exclusions>
			<exclusion>
				<groupId>org.junit.vintage</groupId>
				<artifactId>junit-vintage-engine</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
</dependencies>
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Hoxton.SR1</version>
			<type>pom</type>
			<scope>runtime</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

> 注意springboot和springcloud的版本对应关系，本处使用的是Springboot parent 2.2.4 和 SpringCloud Hoxton.SR1。

Eureka Server 配置：

```yaml
server:
  port: 8761
spring:
  application:
    name: eureka-server
eureka:
  instance:
    hostname: localhost
  server:
    enable-self-preservation: false # 是否开启自我保护模式，默认为true
    eviction-interval-timer-in-ms: 10000 # 续期时间，即扫描失效服务的间隔时间（缺省值为60*1000ms）
  client:
    register-with-eureka: false # 是否将自己作为client注册到eureka-server，默认为true
    fetch-registry: false # 是否拉取eureka注册信息，默认为true
    service-url:
      default-zone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

启用EurekaServer

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
```

### Service

引入maven依赖：

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		<version>2.2.1.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
		<exclusions>
			<exclusion>
				<groupId>org.junit.vintage</groupId>
				<artifactId>junit-vintage-engine</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
</dependencies>
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>Hoxton.SR1</version>
			<type>pom</type>
			<scope>runtime</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

Service配置：

```yaml
server:
  port: 8081
spring:
  application:
    name: eureka-service-provider
eureka:
  instance:
    instance-id: ${spring.application.name}:${server.port}
    prefer-ip-address: true # 设置微服务调用地址为IP优先
    lease-renewal-interval-in-seconds: 30 # 心跳时间，即服务续约间隔时间，缺省值为30s
    lease-expiration-duration-in-seconds: 90 # 发呆时间，即服务续约到期时间（缺省为90s）
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

启用EurekaClient

```java
@SpringBootApplication
@EnableEurekaClient
public class EurekaServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceProviderApplication.class, args);
	}
}
```

Service Provider：

```java
@RestController
@RequestMapping(value = "/provide/")
public class EurekaServiceProviderController {
	@GetMapping(value = "out/{value}")
	public String provide(@PathVariable String value){
		return "EurekaServiceProvider::" + value;
	}
}
```

Service Consumer：

```java
@RestController
@RequestMapping(value = "/consume/")
public class EurekaServiceConsumerController {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "get/{value}")
	public String get(@PathVariable String value){
		String url = "http://eureka-service-provider:8081/provide/out/"+value;
		return restTemplate.getForObject(url, String.class);
	}
}
```

### Eureka 集群配置

集群：将注册中心分别指向其它注册中心。 

```yaml
# application-peer1.yml
server:
  port: 8001
spring:
  application:
    name: eureka-server
  profiles:
    active: peer1
eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8002/eureka/,http://localhost:8003/eureka/
```

```yaml
# application-peer2.yml
server:
  port: 8002
spring:
  application:
    name: eureka-server
  profiles:
    active: peer2
eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8001/eureka/,http://localhost:8003/eureka/
```

```yaml
# application-peer3.yml
server:
  port: 8003
spring:
  application:
    name: eureka-server
  profiles:
    active: peer3
eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8001/eureka/,http://localhost:8002/eureka/
```

使用IDEA配置三个实例：

```
Program arguments: --spring.profiles.active=peer1
Program arguments: --spring.profiles.active=peer2
Program arguments: --spring.profiles.active=peer3
```

## Feign 远程调用

Feign：服务调用。

ServiceConsumer引入maven依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

FeignClient配置文件：

```yaml
feign:
  client:
    config:
      default:
        connect-timeout: 10000
```

**FeginClient**：

```java
@Component
@FeignClient(name = "EUREKA-SERVICE-PROVIDER")
public interface FeignService {
	@GetMapping(value = "/out/{value}")
	String provide(@PathVariable String value);
}
```

> 默认情况下，feign只能通过@RequestBody传对象参数，建议service-provider将为浏览器服务的Controller和为消费者服务的Controller分开。

FeignClient注解属性：

- name：服务名称，同value。

- value：服务名称，同name。

- path： 类似于requestMapping。
- fallback：熔断机制，调用失败时的回调方法。底层依赖hystrix，@EnableHystrix。

### Feign原理

扫包@FeignClient注解类，注入IOC容器。

feign接口被调用时，通过JDK动态代理生成RequestTemplate（包含请求参数、url等），RequestTemplate生成Request交给client处理。client默认为 HTTPUrlConnection，也可以是OKhttp、Apache的HTTPClient等。

最后client封装成LoadBaLanceClient，结合Ribbon负载均衡地发起调用。 

## Ribbon 负载均衡

Ribbon：服务端负载均衡

ServiceProvider引入maven依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
	<version>2.2.1.RELEASE</version>
</dependency>
```

Ribbon配置类：

```java
@Configuration
public class RibbonConfig {
	@Bean
	public IRule ribbonRule(){
		return new RandomRule(); // 随机选择一个server。
//		return new BestAvailableRule(); // 逐个检查，选择并发请求最小的server。
//		return new RoundRobinRule(); // 轮询选择server。
//		return new WeightedResponseTimeRule(); // 根据响应时间分配权重，权重选择。
//		return new ZoneAvoidanceRule(); // 根据server性能和server可用性选择。
//		return new RetryRule(); // 对选定的负载均衡策略机上重试机制，在一个配置时间段内当选择server不成功，则一直尝试使用subRule的方式选择一个可用的server
	}

	@Bean
	public IPing ribbonPing(){
		return new PingUrl();
	}

	@Bean
	public ServerListSubsetFilter serverListSubsetFilter(){
		ServerListSubsetFilter filter = new ServerListSubsetFilter();
		return filter;
	}
}
```

ServiceProvider添加注解@RibbonClient：

```java
@EnableEurekaClient
@RibbonClient(name = "RibbonClient", configuration = RibbonConfig.class)
@SpringBootApplication
public class EurekaServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceProviderApplication.class, args);
	}
}
```

ServiceProvider测试：

```java
@RestController
@RequestMapping(value = "/ribbon/")
public class RibbonController {
	@Value("${spring.application.name}")
	private String appName;
	@Value("${server.port}")
	private String port;
	
	@GetMapping(value = "port")
	public String ribbon(){
		return appName + "::" + port;
	}
}
```

ServiceConsumer测试：

```java
@Component
@FeignClient(name = "EUREKA-SERVICE-PROVIDER")
public interface ProviderFeignClient {
	@GetMapping(value = "/ribbon/port")
	String ribbon();
}
```

ServiceProvider启动两个段端口不同的实例，ServiceConsumer调用，可以看到每次调用port值不同。

## Hystrix 熔断机制

熔断器：消费端监控服务故障，连续多次失败调用通过Fallback进行熔断保护。

ServiceConsumer引入Maven依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.2.1.RELEASE</version>
</dependency>
```

ServiceConsumer配置文件：

```yaml
feign:
  hystrix:
    enabled: true
```

FeignClient：

```java
@Component
@FeignClient(name = "EUREKA-SERVICE-PROVIDER", fallback = ProviderFeignClientFallback.class)
public interface ProviderFeignClient {
	@GetMapping(value = "/provide/out/{value}")
	String provide(@PathVariable String value);

	@GetMapping(value = "/ribbon/port")
	String ribbon();
}
```

FeignClienFallback：

```java
@Component
public class ProviderFeignClientFallback implements ProviderFeignClient {
	@Override
	public String provide(String value) {
		return "Out Error";
	}
	@Override
	public String ribbon() {
		return "Port Error";
	}
}
```
