package com.openclassrooms.mdd.service.query;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.mapper.UserMapper;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class UserQueryService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse findById(Long id) throws NoSuchElementException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username);
        } else {
            user = userRepository.findByUsername(username);
        }
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

}
