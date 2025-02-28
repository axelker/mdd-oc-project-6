package com.openclassrooms.mdd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByArticleIdAndUserId(Long articleId,Long userId);
}
