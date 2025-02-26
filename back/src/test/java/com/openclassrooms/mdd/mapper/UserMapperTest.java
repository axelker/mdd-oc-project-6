package com.openclassrooms.mdd.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.openclassrooms.mdd.dto.request.AuthRegisterRequest;
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
                .name("test")
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .password("test-secret")
                .build();

        UserResponse userResponse = userMapper.toDto(userEntity);

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(userEntity.getId());
        assertThat(userResponse.getName()).isEqualTo(userEntity.getName());
        assertThat(userResponse.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(userResponse.getCreated_at()).isEqualTo(userEntity.getCreated_at());
        assertThat(userResponse.getUpdated_at()).isEqualTo(userEntity.getUpdated_at());
    }

    @Test
    void shouldMapAuthRegisterRequestToUserEntity() {
        AuthRegisterRequest request = AuthRegisterRequest.builder()
                .email("test@test.fr")
                .name("test")
                .password("test-secret")
                .build();

        UserEntity userEntity = userMapper.authRegisterToEntity(request);

        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getName()).isEqualTo(request.getName());
        assertThat(userEntity.getEmail()).isEqualTo(request.getEmail());
        assertThat(userEntity.getPassword()).isEqualTo(request.getPassword());
    }
}