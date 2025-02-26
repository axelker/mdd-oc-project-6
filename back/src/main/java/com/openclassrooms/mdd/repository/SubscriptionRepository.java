package com.openclassrooms.mdd.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.SubscriptionEntity;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long>  {
    
}
