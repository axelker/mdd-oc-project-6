package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.dto.response.UserResponse;
import com.openclassrooms.mdd.service.query.UserQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Users", description = "Manage user accounts")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserQueryService userQueryService;

    public UserRestController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
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

}
