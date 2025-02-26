package com.openclassrooms.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.dto.response.ThemeResponse;
import com.openclassrooms.mdd.model.ThemeEntity;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeMapper INSTANCE = Mappers.getMapper(ThemeMapper.class);

    ThemeResponse toDto(ThemeEntity entity);

}
