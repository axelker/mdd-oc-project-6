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
    @Mapping(target = "ownerId", source = "ownerId")
    ArticleEntity toEntity(ArticleRequest dto,Long ownerId);

    
    ArticleResponse toDto(ArticleEntity entity);

}
