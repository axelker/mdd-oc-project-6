package com.openclassrooms.mdd.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.mapper.UserMapper;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;
import com.openclassrooms.mdd.service.validation.UserValidationService;

@Service
public class UserCommandeService {
    private final UserValidationService userValidationService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserCommandeService(UserRepository userRepository,UserMapper userMapper,UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidationService = userValidationService;
    }

    public UserResponse create(UserRequest request) {
        this.userValidationService.validateUser(request,null);
        UserEntity userToSave = userMapper.toEntity(request).toBuilder().build();
        return userMapper.toDto(userRepository.save(userToSave));
    }
    public UserResponse update(UserRequest request, Long userId) {
        this.userValidationService.validateUser(request,userId);
        UserEntity userToSave = userMapper.toEntity(request).toBuilder().id(userId).build();
        return userMapper.toDto(userRepository.save(userToSave));
    }


}
