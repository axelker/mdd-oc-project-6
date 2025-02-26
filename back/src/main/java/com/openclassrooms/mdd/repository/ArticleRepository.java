package com.openclassrooms.mdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mdd.model.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
