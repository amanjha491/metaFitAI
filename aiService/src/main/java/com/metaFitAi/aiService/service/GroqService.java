package com.metaFitAi.aiService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.Map;

@Service
@Slf4j
public class GroqService {
    private final WebClient webClient;

    @Value("${groq.api.url}")
    private String groqApiUrl;

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.api.model}")
    private String groqModel;

    public GroqService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @CircuitBreaker(name = "groqAi", fallbackMethod = "fallbackRecommendations")
    public String getRecommendations(String details) {

        Map<String, Object> requestBody = Map.of(
                "messages", new Object[]{Map.of("role", "user", "content", details)},
                "model", groqModel,
                "temperature", 1.0,
                "max_tokens", 8192,
                "top_p", 1.0,
                "stream", false
        );

        String response = webClient.post()
                .uri(groqApiUrl)
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + groqApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    public String fallbackRecommendations(String details, Throwable t) {
        log.error("GROQ API failed, using fallback recommendations. Error: {}", t.getMessage());
        return "Generic Recommendation: Due to high AI load, here are standard guidelines: Drink water, stretch, and maintain 10k steps. " +
               "Your original details were logged.";
    }
}
