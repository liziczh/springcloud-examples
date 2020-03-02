## Config 统一配置

**ConfigServer**：

引入maven依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
	<version>2.2.1.RELEASE</version>
</dependency>
```

ConfigServer配置文件：application.yml

```yaml
spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/liziczh/lizi-nacos  # git仓库地址
          default-label: master # git分支
          search-paths: springcloud-config/config # 配置文件根目录
          username: liziczh  # 公开项目，无需配置
          password: xxxxxx   # 公开项目，无需配置
```

SpringBootApplication：

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
```

**SpringCloud Config的URL与配置文件的映射关系**：

```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

启动项目，按照URL映射关系即可查看配置文件。

**ConfigClient**：

引入maven依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
	<version>2.2.1.RELEASE</version>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

ConfigClient配置文件：`bootstrap.yml` 

> 注意配置文件的名称为bootstrap.yml，bootstrap比application加载早。

```yaml
server:
  port: 9002
spring:
  application:
    name: config-client
  cloud:
    config:
      uri: http://localhost:9001/ # config-server
      label: master # git分支
      name: config-client  # 配置文件的名称
      profile: pro # 获取配置的策略
```

ConfigController：

```java
@RestController
@RequestMapping("/config/")
@RefreshScope
public class SpringCloudConfigController {
	@Value(value = "${db.username:admin}")
	private String username;
	@GetMapping(value = "testConfig")
	public String testConfig() {
		return "username:" + username;
	}
}
```



