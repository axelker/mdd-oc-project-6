package com.openclassrooms.mdd.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
}
