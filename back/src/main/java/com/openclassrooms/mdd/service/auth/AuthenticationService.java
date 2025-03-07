package com.openclassrooms.mdd.service.auth;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.AuthLoginRequest;
import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.UserRepository;
import com.openclassrooms.mdd.service.command.UserCommandeService;

@Service
public class AuthenticationService {
    private final UserCommandeService userCommandeService;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserCommandeService userCommandeService,
            UserRepository userRepository,
            JWTService jwtService,
            AuthenticationManager authenticationManager) {
        this.userCommandeService = userCommandeService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseCookie register(UserRequest request) throws Exception {
        UserResponse userResponse = this.userCommandeService.create(request);
        UserEntity user = userRepository.findById(userResponse.getId())
            .orElseThrow(() -> new NoSuchElementException("User not found after creation."));
        return jwtService.generateJwtCookie(user);
    }

    public ResponseCookie authenticate(AuthLoginRequest request) {
        try {
            var test = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getIdentifier(),
                            request.getPassword()));

            UserEntity user = (UserEntity) test.getPrincipal();
            return jwtService.generateJwtCookie(user);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.");
        }
    }

    public ResponseCookie logout() {
        return jwtService.generateJwtLogoutCookie();
    }

}