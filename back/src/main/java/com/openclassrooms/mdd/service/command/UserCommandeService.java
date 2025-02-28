package com.openclassrooms.mdd.service.command;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.mapper.UserMapper;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class UserCommandeService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserCommandeService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse update(UserRequest request, Long userId){
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        
        UserEntity userToSave = user.toBuilder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        return userMapper.toDto(userRepository.save(userToSave));
    }
}
