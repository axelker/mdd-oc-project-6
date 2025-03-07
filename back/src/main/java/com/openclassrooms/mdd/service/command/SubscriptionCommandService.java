package com.openclassrooms.mdd.service.command;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.exception.UnauthorizedActionException;
import com.openclassrooms.mdd.model.SubscriptionEntity;
import com.openclassrooms.mdd.model.ThemeEntity;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.SubscriptionRepository;
import com.openclassrooms.mdd.repository.ThemeRepository;
import com.openclassrooms.mdd.repository.UserRepository;

/**
 * Service for handling theme subscriptions.
 * <p>
 * This service provides functionality to subscribe and unsubscribe users
 * from themes while ensuring data integrity.
 * </p>
 */
@Service
public class SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    /**
     * Constructs an instance of {@code SubscriptionCommandService} with required
     * dependencies.
     *
     * @param subscriptionRepository the repository for managing subscriptions.
     * @param userRepository         the repository for retrieving users.
     * @param themeRepository        the repository for retrieving themes.
     */
    public SubscriptionCommandService(SubscriptionRepository subscriptionRepository,
            UserRepository userRepository,
            ThemeRepository themeRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
    }

    /**
     * Subscribes a user to a theme.
     * <p>
     * Before subscribing, this method ensures that both the user and theme exist.
     * If the user is already subscribed, an {@link IllegalStateException} is
     * thrown.
     * </p>
     *
     * @param themeId the ID of the theme to subscribe to.
     * @param userId  the ID of the user subscribing.
     * @throws NoSuchElementException if the user or theme does not exist.
     * @throws IllegalStateException  if the user is already subscribed to the
     *                                theme.
     */
    public void subscribe(Long themeId, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        ThemeEntity theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with ID: " + themeId));

        boolean alreadySubscribed = subscriptionRepository.findByUserIdAndThemeId(userId, themeId).isPresent();
        if (alreadySubscribed) {
            throw new IllegalStateException("User is already subscribed to this theme.");
        }

        SubscriptionEntity sub = SubscriptionEntity.builder()
                .user(user)
                .theme(theme)
                .build();
        subscriptionRepository.save(sub);
    }

    /**
     * Unsubscribes a user from a theme.
     * <p>
     * This method checks if the subscription exists before attempting to delete it.
     * If the subscription does not exist, a {@link NoSuchElementException} is
     * thrown.
     * If the user is not the owner of the subscription, an
     * {@link UnauthorizedActionException} is thrown.
     * </p>
     *
     * @param themeId the ID of the theme to unsubscribe from.
     * @param userId  the ID of the user unsubscribing.
     * @throws NoSuchElementException      if the subscription does not exist.
     * @throws UnauthorizedActionException if the user is not authorized to delete
     *                                     the subscription.
     */
    public void unsubscribe(Long themeId, Long userId) {
        SubscriptionEntity sub = subscriptionRepository.findByUserIdAndThemeId(userId, themeId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Subscription not found for user ID " + userId + " and theme ID " + themeId));

        if (!sub.getUser().getId().equals(userId)) {
            throw new UnauthorizedActionException("You are not authorized to delete this subscription.");
        }

        subscriptionRepository.deleteById(sub.getId());
    }
}
