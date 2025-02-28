package com.openclassrooms.mdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mdd.model.ThemeEntity;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Long>  {
    
}
