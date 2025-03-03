package com.openclassrooms.mdd.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.AuthLoginRequest;
import com.openclassrooms.mdd.dto.request.AuthRegisterRequest;
import com.openclassrooms.mdd.dto.response.AuthResponse;
import com.openclassrooms.mdd.exception.user.UserAlreadyExistsException;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthResponse register(AuthRegisterRequest request) throws Exception {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exist with the same email.");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exist with the same username.");
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(AuthLoginRequest request) {
        try {
            var test = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getIdentifier(),
                            request.getPassword()));

            UserEntity user = (UserEntity) test.getPrincipal();
            String token = jwtService.generateToken(user);
            return AuthResponse.builder().token(token).build();

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.");
        }
    }

}