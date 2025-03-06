package com.openclassrooms.mdd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openclassrooms.mdd.model.ThemeEntity;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Long>  {
     @Query("""
        SELECT t FROM ThemeEntity t
        WHERE (:subscribed IS NULL) 
           OR (:subscribed = TRUE AND t.id IN (SELECT s.theme.id FROM SubscriptionEntity s WHERE s.user.id = :userId))
           OR (:subscribed = FALSE AND t.id NOT IN (SELECT s.theme.id FROM SubscriptionEntity s WHERE s.user.id = :userId))
    """)
    List<ThemeEntity> findThemesBySubscriptionStatus(@Param("userId") Long userId, @Param("subscribed") Boolean subscribed);

}
