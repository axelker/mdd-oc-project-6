package com.openclassrooms.mdd.service.validation;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.exception.user.UserAlreadyExistsException;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class UserValidationService {
    private final UserRepository userRepository ;

    UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void validAlreadyUsedUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User already exist with the same username.");
        }
    }
    private void validAlreadyUsedEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User already exist with the same email.");
        }
    }

    public void validateUser(UserRequest request, Long userId) {
        UserEntity existUser = null;
        if (null != userId) {
            existUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        }
        
        if (null == existUser || !existUser.getEmail().equals(request.getEmail())) {
            this.validAlreadyUsedEmail(request.getEmail());
        }
        if (null == existUser || !existUser.getUsername().equals(request.getUsername())) {
            this.validAlreadyUsedUsername(request.getUsername());
        }
    }
}
