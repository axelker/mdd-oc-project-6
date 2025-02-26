package com.openclassrooms.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.model.CommentEntity;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.model.UserEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "user", source = "user_id", qualifiedByName = "mapUserById")
    @Mapping(target = "article", source = "article_id", qualifiedByName = "mapArticleById")
    CommentEntity toEntity(CommentRequest message);

    @Named("mapUserById")
    default UserEntity mapUserById(Long userId) {
        return userId == null ? null : UserEntity.builder().id(userId).build();
    }

    @Named("mapArticleById")
    default ArticleEntity mapArticleById(Long article_id) {
        return article_id == null ? null : ArticleEntity.builder().id(article_id).build();
    }
}
