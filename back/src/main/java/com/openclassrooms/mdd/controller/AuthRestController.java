package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.request.AuthLoginRequest;
import com.openclassrooms.mdd.dto.request.AuthRegisterRequest;
import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.AuthResponse;
import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.service.auth.AuthenticationService;
import com.openclassrooms.mdd.service.auth.JWTService;
import com.openclassrooms.mdd.service.command.UserCommandeService;
import com.openclassrooms.mdd.service.query.UserQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Authentication", description = "User authentication and registration")
@ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthenticationService authService;
    private final UserQueryService userQueryService;
    private final UserCommandeService userCommandService;
    private final JWTService jwtService;

    AuthRestController(AuthenticationService authService,UserQueryService userQueryService,UserCommandeService userCommandService,JWTService jwtService) {
        this.authService = authService;
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Find info of current user authenticate", description = "Retrieve details of a user using its authentication information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User info found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping("me")
    public ResponseEntity<UserResponse> getMe(Authentication authentication) {
        Long userId = jwtService.getUserId(authentication);
        return ResponseEntity.ok(userQueryService.findById(userId));
    }

    @Operation(
        summary = "Update current user",
        description = "Allows the authenticated user to update their profile information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input (e.g., missing fields, incorrect format)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found - User does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("me")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest body,Authentication authentication) {
        Long userId = jwtService.getUserId(authentication);
        return ResponseEntity.ok(userCommandService.update(body,userId));
    }

    @Operation(summary = "User Login", description = "Authenticate user and return a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest body) {
        return ResponseEntity.ok(authService.authenticate(body));
    }

    @Operation(summary = "User Registration", description = "Register a new user and return a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User register successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict : User already exist with the same email in the system.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRegisterRequest body) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(body));
    }

}
