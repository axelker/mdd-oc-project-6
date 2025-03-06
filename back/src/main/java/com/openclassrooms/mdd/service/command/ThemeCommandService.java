package com.openclassrooms.mdd.service.command;

import org.springframework.stereotype.Service;

@Service
public class ThemeCommandService {
    private final SubscriptionCommandService subscriptionCommandService;

    ThemeCommandService(SubscriptionCommandService subscriptionCommandService) {
        this.subscriptionCommandService = subscriptionCommandService;
    }

    public void subscribe(Long themeId, Long userId) {
        subscriptionCommandService.subscribe(themeId, userId);
    }

    public void unsubscribe(Long themeId, Long userId) {
        subscriptionCommandService.unsubscribe(themeId, userId);
    }
}
