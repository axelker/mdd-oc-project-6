package com.openclassrooms.mdd.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.SubscriptionEntity;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long>  {
    Optional<SubscriptionEntity> findByUserIdAndThemeId(Long userId,Long themeId);
}
