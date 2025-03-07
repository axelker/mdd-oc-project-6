package com.openclassrooms.mdd.service.command;

import org.springframework.stereotype.Service;

/**
 * Service for handling theme-related writable operations.
 * <p>
 * This service delegates subscription management to the {@link SubscriptionCommandService}.
 * It provides methods for subscribing and unsubscribing users to/from themes.
 * </p>
 */
@Service
public class ThemeCommandService {

    private final SubscriptionCommandService subscriptionCommandService;

    /**
     * Constructs an instance of {@code ThemeCommandService} with the required {@link SubscriptionCommandService}.
     *
     * @param subscriptionCommandService the service responsible for managing subscriptions.
     */
    ThemeCommandService(SubscriptionCommandService subscriptionCommandService) {
        this.subscriptionCommandService = subscriptionCommandService;
    }

    /**
     * Subscribes a user to a theme.
     * <p>
     * This method delegates the task to the {@link SubscriptionCommandService}.
     * </p>
     *
     * @param themeId the ID of the theme to subscribe to.
     * @param userId  the ID of the user subscribing.
     */
    public void subscribe(Long themeId, Long userId) {
        subscriptionCommandService.subscribe(themeId, userId);
    }

    /**
     * Unsubscribes a user from a theme.
     * <p>
     * This method delegates the task to the {@link SubscriptionCommandService}.
     * </p>
     *
     * @param themeId the ID of the theme to unsubscribe from.
     * @param userId  the ID of the user unsubscribing.
     */
    public void unsubscribe(Long themeId, Long userId) {
        subscriptionCommandService.unsubscribe(themeId, userId);
    }
}
