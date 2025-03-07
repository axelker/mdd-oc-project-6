package com.openclassrooms.mdd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.SubscriptionEntity;

/**
 * Repository interface for managing {@link SubscriptionEntity} persistence.
 * <p>
 * This interface extends {@link CrudRepository} to provide basic CRUD operations
 * for the {@code subscriptions} table. Custom query methods are included for 
 * retrieving user subscriptions.
 * </p>
 */
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long> {

    /**
     * Finds a subscription for a specific user and theme.
     *
     * @param userId  the ID of the user.
     * @param themeId the ID of the theme.
     * @return an {@link Optional} containing the subscription if found, otherwise empty.
     */
    Optional<SubscriptionEntity> findByUserIdAndThemeId(Long userId, Long themeId);

    /**
     * Retrieves all subscriptions for a given user.
     *
     * @param userId the ID of the user.
     * @return a list of {@link SubscriptionEntity} representing the user's subscriptions.
     */
    List<SubscriptionEntity> findAllByUserId(Long userId);
}
