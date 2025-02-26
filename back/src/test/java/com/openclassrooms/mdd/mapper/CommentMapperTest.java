package com.openclassrooms.mdd.mapper;

import org.junit.jupiter.api.Test;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.model.CommentEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentMapperTest {
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Test
    void shouldMapCommentRequestNullToNull() {
        CommentRequest commentRequest = null;
        CommentEntity commentEntity = commentMapper.toEntity(commentRequest);

        assertThat(commentEntity).isNull();
    }

    @Test
    void shouldMapMapCommentRequestToCommentEntity() {
        CommentRequest commentRequest = CommentRequest.builder().userId(1L).articleId(2L).message("test").build();
        CommentEntity commentEntity = commentMapper.toEntity(commentRequest);

        assertThat(commentEntity).isNotNull();
        assertThat(commentEntity.getUser().getId()).isEqualTo(commentRequest.getUserId());
        assertThat(commentEntity.getArticle().getId()).isEqualTo(commentRequest.getArticleId());
        assertThat(commentEntity.getMessage()).isEqualTo(commentRequest.getMessage());
    }
}
