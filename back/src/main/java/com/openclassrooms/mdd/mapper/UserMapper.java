package com.openclassrooms.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.dto.request.AuthRegisterRequest;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    UserEntity authRegisterToEntity(AuthRegisterRequest request);
}