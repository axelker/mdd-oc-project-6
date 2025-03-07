package com.openclassrooms.mdd.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mdd.model.ArticleEntity;

/**
 * Repository interface for managing {@link ArticleEntity} persistence.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * for the {@code articles} table. Custom query methods can be added as needed.
 * </p>
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    /**
     * Retrieves all articles belonging to a list of theme IDs, sorted by the given criteria.
     *
     * @param themeIds the list of theme IDs to filter articles.
     * @param sort     the sorting criteria to apply.
     * @return a list of {@link ArticleEntity} matching the given theme IDs.
     */
    List<ArticleEntity> findAllByThemeIdIn(List<Long> themeIds, Sort sort);
}
