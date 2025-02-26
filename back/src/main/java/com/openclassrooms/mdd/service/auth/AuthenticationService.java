package com.openclassrooms.mdd.service.auth;

import java.util.NoSuchElementException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.AuthLoginRequest;
import com.openclassrooms.mdd.dto.request.AuthRegisterRequest;
import com.openclassrooms.mdd.dto.response.AuthResponse;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.exception.user.UserAlreadyExistsException;
import com.openclassrooms.mdd.mapper.UserMapper;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTService jwtService,
            UserMapper userMapper,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserResponse getUserInfo(Authentication authentication) {

        Long userId = jwtService.getUserId(authentication);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.toDto(user);
    }

    public AuthResponse register(AuthRegisterRequest request) throws Exception {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exist.");
        }

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(AuthLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            UserEntity user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new NoSuchElementException("User not found after authentication"));

            String token = jwtService.generateToken(user);
            return AuthResponse.builder().token(token).build();

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.");
        }
    }

}