package com.liziczh.service.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.ServerListSubsetFilter;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;

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
