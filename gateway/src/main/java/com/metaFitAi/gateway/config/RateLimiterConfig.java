package com.metaFitAi.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {

    @Bean
    public KeyResolver userKeyResolver() {
        // Rate limit by client IP address
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        
        // Alternative: Rate limit by User ID from JWT
        // return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user-id"))
        //         .defaultIfEmpty("anonymous");
    }
}
