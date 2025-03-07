package com.openclassrooms.mdd.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.repository.SubscriptionRepository;

/**
 * Service for querying user subscription data.
 * <p>
 * This service provides functionality for retrieving theme IDs for which a specific user is subscribed.
 * </p>
 */
@Service
public class SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * Constructs an instance of {@code SubscriptionQueryService} with the required repository.
     *
     * @param subscriptionRepository the repository for managing user subscriptions.
     */
    SubscriptionQueryService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Retrieves the list of theme IDs that a specific user is subscribed to.
     * <p>
     * This method fetches all the subscriptions for the user and extracts the IDs of the themes
     * the user is subscribed to.
     * </p>
     *
     * @param userId the ID of the user.
     * @return a list of theme IDs that the user is subscribed to.
     */
    public List<Long> getThemeIdsUserSub(Long userId) {
        List<Long> userSubThemeIds = subscriptionRepository.findAllByUserId(userId).stream()
                .map(sub -> sub.getTheme().getId())
                .toList();

        return userSubThemeIds;
    }
}
