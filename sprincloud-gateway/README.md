## Gateway 服务网关

Route（路由）：由一个RouteID、目标URL、一组断言、一组过滤器定义。如果断言为真，则路由匹配。

引入maven依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-gateway</artifactId>
	<version>2.2.1.RELEASE</version>
</dependency>
```

配置文件：`application.yml` 

```yaml
spring:
  application:
    name: gateway-service-a
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true  # 启用注册中心定位网关服务，启用后可直接使用应用名程调用服务
      routes:
          # 路由标识
        - id: gateway-service-a
          # 目标服务URL
          uri: https://www.baidu.com
          # 断言
          predicates:
            - After=2019-01-01T00:00:00+08:00[Asia/Shanghai]
#            - Before=2049-01-01T00:00:00+08:00[Asia/Shanghai]
#            - Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2049-01-01T00:00:00+08:00[Asia/Shanghai]
#            - Cookie=sessionId, test
#            - Header=X-Request-Id, \d+
#            - Host=**.baidu.com
#            - Method=GET
#            - Path=/a
#            - Query=name
#            - RemoteAddr=192.168.1.1/24
#          filter:
#            # 截取Path位数
#            - StripPrefix=1
```

 Predicate：匹配成功跳转目标URL。

1.在某个时间之后的请求都进行转发

```yaml
- After=2019-01-01T00:00:00+08:00[Asia/Shanghai]
```

2.在某个时间之前的请求都进行转发

```yaml
- Before=2019-01-01T00:00:00+08:00[Asia/Shanghai]
```

3.在某个时间段的请求都进行转发

```yaml
- Between=2019-01-01T00:00:00+08:00[Asia/Shanghai], 2019-07-01T00:00:00+08:00[Asia/Shanghai]
```

4.Cookie匹配：Cookie Route Predicate

```yaml
- Cookie=sessionId, test
```

测试：`curl http://localhost:8080 --cookie "sessionId=test"`  

5.请求头匹配：Header Route Predicate：

```yaml
- Header=X-Request-Id, \d+
```

测试：`curl http://localhost:8080  -H "X-Request-Id:88"`  

6.Host匹配：Host Route Predicate

```yaml
- Host=**.baidu.com
```

测试：` curl http://localhost:8080  -H "Host: www.baidu.com" `  

7.请求方式匹配：

```yaml
- Method=GET
```

测试：` curl -X GET http://localhost:8080 `

8.请求路径匹配：Path Route Predicate 

```
- Path=/hello
```

测试：` curl http://localhost:8080/hello `  

9.请求参数匹配：

```
- Query=name
```

测试：` curl localhost:8080?name=tom&id=2 `   

10.请求IP地址匹配：

```
- RemoteAddr=192.168.1.1/24
```

测试：` curl localhost:8080 `  
