package com.openclassrooms.mdd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.CommentEntity;

/**
 * Repository interface for managing {@link CommentEntity} persistence.
 * <p>
 * This interface extends {@link CrudRepository} to provide basic CRUD operations
 * for the {@code comments} table. Custom query methods can be added as needed.
 * </p>
 */
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    /**
     * Retrieves all comments associated with a specific article.
     *
     * @param articleId the ID of the article for which comments are retrieved.
     * @return a list of {@link CommentEntity} linked to the given article ID.
     */
    List<CommentEntity> findAllByArticleId(Long articleId);
}
