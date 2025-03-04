package com.openclassrooms.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.dto.request.ArticleRequest;
import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.model.ArticleEntity;
;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "theme.id", source = "dto.themeId")
    ArticleEntity toEntity(ArticleRequest dto,Long ownerId);


    @Mapping(target = "owner.id", source = "owner.id")
    @Mapping(target = "theme.id", source = "theme.id")
    @Mapping(target = "owner.name", source = "owner.username")
    @Mapping(target = "theme.name", source = "theme.name")
    ArticleResponse toDto(ArticleEntity entity);

}
