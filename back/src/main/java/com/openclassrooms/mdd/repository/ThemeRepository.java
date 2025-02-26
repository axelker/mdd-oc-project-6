package com.openclassrooms.mdd.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.ThemeEntity;

public interface ThemeRepository extends CrudRepository<ThemeEntity, Long>  {
    
}
