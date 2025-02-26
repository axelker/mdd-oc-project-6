package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.request.UserRequest;
import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.dto.response.UserResponse;
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

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Users", description = "Manage user accounts")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserQueryService userQueryService;
    private final UserCommandeService userCommandService;
    private final JWTService jwtService;


    public UserRestController(UserQueryService userQueryService,UserCommandeService userCommandService,JWTService jwtService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Find a user by ID", description = "Retrieve details of a user property using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userQueryService.getUserById(id));
    }

    @Operation(summary = "Update user", description = "Update the user provided.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest body,Authentication authentication) {
        Long userId = jwtService.getUserId(authentication);
        return ResponseEntity.ok(userCommandService.update(body,userId));
    }

}
