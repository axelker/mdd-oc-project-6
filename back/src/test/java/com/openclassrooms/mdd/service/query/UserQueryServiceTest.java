package com.openclassrooms.mdd.service.query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.mapper.UserMapper;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;
import com.openclassrooms.mdd.service.query.UserQueryService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserQueryService userQueryService;

    @Test
    void findById_shouldReturnUser_whenUserExists() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("test@test.fr")
                .username("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .password("test-secret")
                .build();

        UserResponse userResponse = UserResponse.builder().id(1L).email("test@test.fr").username("test").build();
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(userEntity)).thenReturn(userResponse);
        var result = userQueryService.findById(1L);

        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getUsername(), result.getUsername());
        verify(userMapper, times(1)).toDto(userEntity);

    }

    @Test
    void findById_shouldThrowException_whenUserDoesNotExist() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userQueryService.findById(2L);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userMapper, never()).toDto(any());

    }
}
