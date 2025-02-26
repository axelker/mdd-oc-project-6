package com.openclassrooms.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.model.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "article", ignore = true)
    CommentEntity toEntity(CommentRequest message);

    @Mapping(target = "ownerId", source = "user.id")
    @Mapping(target = "articleId", source = "article.id")
    CommentResponse toDto(CommentEntity comment);

}
