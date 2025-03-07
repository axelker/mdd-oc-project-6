package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.request.AuthLoginRequest;
import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.dto.response.Response;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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

    AuthRestController(AuthenticationService authService, UserQueryService userQueryService,
            UserCommandeService userCommandService, JWTService jwtService) {
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
    public ResponseEntity<UserResponse> getMe(@CookieValue(name = "jwt") String jwtToken) {
        Long userId = jwtService.extractUserId(jwtToken);
        return ResponseEntity.ok(userQueryService.findById(userId));
    }

    @Operation(summary = "Update current user", description = "Allows the authenticated user to update their profile information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input (e.g., missing fields, incorrect format)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found - User does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("me")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest body,
            @CookieValue(name = "jwt") String jwtToken) {
        Long userId = jwtService.extractUserId(jwtToken);
        ResponseCookie jwtCookie = userCommandService.update(body, userId);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(userQueryService.findById(userId));
    }

    @Operation(summary = "User Login", description = "Authenticate user and return a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("login")
    public ResponseEntity<Response> login(@Valid @RequestBody AuthLoginRequest body) {
        ResponseCookie jwtCookie = authService.authenticate(body);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Response.builder().message("Login successful.").build());
    }

    @Operation(summary = "User Registration", description = "Register a new user and return a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User register successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "409", description = "Conflict : User already exist with the same email in the system.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("register")
    public ResponseEntity<Response> register(@Valid @RequestBody UserRequest body) throws Exception {
        authService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.builder().message("Register successful.").build());
    }

    @Operation(summary = "User Logout", description = "Logout the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Logout successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
    })
    @GetMapping("logout")
    public ResponseEntity<Response> logout() throws Exception {
        ResponseCookie logoutCookie = authService.logout();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, logoutCookie.toString())
                .body(Response.builder().message("Logout successful.").build());
    }

}
