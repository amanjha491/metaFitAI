package com.metaFitAi.aiService.service;

import com.metaFitAi.aiService.model.Recommendation;
import com.metaFitAi.aiService.respository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    @Cacheable(value = "userRecommendations", key = "#userId")
    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    @Cacheable(value = "activityRecommendations", key = "#activityId")
    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for this activity: " + activityId));
    }
}
