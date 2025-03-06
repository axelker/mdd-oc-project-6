package com.openclassrooms.mdd.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.model.UserEntity;

public class UserMapperTest {
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void shouldMapUserEntityNullToNull() {
        UserEntity userEntity = null;
        UserResponse userResponse = userMapper.toDto(userEntity);

        assertThat(userResponse).isNull();
    }

    @Test
    void shouldMapUserEntityToUserResponse() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("test@test.fr")
                .username("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .password("test-secret")
                .build();

        UserResponse userResponse = userMapper.toDto(userEntity);

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(userEntity.getId());
        assertThat(userResponse.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(userResponse.getEmail()).isEqualTo(userEntity.getEmail());
    }

    @Test
    void shouldMapUserRequestToUserEntity() {
        UserRequest request = UserRequest.builder()
                .email("test@test.fr")
                .username("test")
                .password("test-secret")
                .build();

        UserEntity userEntity = userMapper.toEntity(request);

        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getUsername()).isEqualTo(request.getUsername());
        assertThat(userEntity.getEmail()).isEqualTo(request.getEmail());
        assertThat(userEntity.getPassword()).isEqualTo(request.getPassword());
    }
}