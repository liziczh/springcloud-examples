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

ConfigServer配置文件：`application.yml`  

```yaml
spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          # git仓库地址
          uri: https://github.com/liziczh/lizi-springcloud
          # git分支
          default-label: master
          # 配置文件根目录
          search-paths: springcloud-config/config
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

```yaml
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
spring:
  application:
    name: config-client
  cloud:
    config:
      # 配置中心地址
      uri: http://localhost:9001/
      # git分支
      label: master
      # 配置文件的名称
      name: config-client
      # 获取配置的策略
      profile: pro
      # 配置发现
      discovery:
        enabled: true
        # 指定配置中心的service-id
        service-id: config-server
```

ConfigController：

```java
@RestController
@RequestMapping("/config/")
@RefreshScope
public class SpringCloudConfigController {
	@Value(value = "${db.username:admin}")
	private String username;
	@GetMapping(value = "test")
	public String test() {
		return "username:" + username;
	}
}
```

