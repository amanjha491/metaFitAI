package com.metaFitAi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.cloud.gateway.filter.ratelimit.KeyResolver userKeyResolver() {
		return exchange -> reactor.core.publisher.Mono.just(
				exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
		);
	}

}
