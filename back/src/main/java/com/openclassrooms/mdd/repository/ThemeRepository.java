package com.openclassrooms.mdd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openclassrooms.mdd.model.ThemeEntity;

/**
 * Repository interface for managing {@link ThemeEntity} persistence.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * for the {@code themes} table. Custom query methods allow filtering themes
 * based on user subscription status.
 * </p>
 */
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {

    /**
     * Retrieves themes based on the subscription status of a user.
     * <p>
     * If {@code subscribed} is:
     * <ul>
     *     <li>{@code NULL} → Returns all themes.</li>
     *     <li>{@code TRUE} → Returns only themes the user is subscribed to.</li>
     *     <li>{@code FALSE} → Returns only themes the user is not subscribed to.</li>
     * </ul>
     * </p>
     *
     * @param userId     the ID of the user.
     * @param subscribed the subscription filter (true = subscribed, false = not subscribed, null = all).
     * @return a list of {@link ThemeEntity} filtered based on the subscription status.
     */
    @Query("""
        SELECT t FROM ThemeEntity t
        WHERE (:subscribed IS NULL) 
           OR (:subscribed = TRUE AND t.id IN (SELECT s.theme.id FROM SubscriptionEntity s WHERE s.user.id = :userId))
           OR (:subscribed = FALSE AND t.id NOT IN (SELECT s.theme.id FROM SubscriptionEntity s WHERE s.user.id = :userId))
    """)
    List<ThemeEntity> findThemesBySubscriptionStatus(@Param("userId") Long userId, @Param("subscribed") Boolean subscribed);
}
