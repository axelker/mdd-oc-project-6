package com.openclassrooms.mdd.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.repository.SubscriptionRepository;

@Service
public class SubscriptionQueryService {
    private final SubscriptionRepository subscriptionRepository;

    SubscriptionQueryService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    
    public List<Long> getThemeIdsUserSub(Long userId) {
        List<Long> userSubThemeIds = subscriptionRepository.findAllByUserId(userId).stream()
                .map(sub -> sub.getTheme().getId()).toList();

        return userSubThemeIds;
    }
}
