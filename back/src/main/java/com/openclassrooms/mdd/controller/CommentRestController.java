package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.service.auth.JWTService;
import com.openclassrooms.mdd.service.command.CommentCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Comments", description = "Manage user comments on article")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/comments")
public class CommentRestController {
    private final CommentCommandService commentCommandService;
    private final JWTService jwtService;

    public CommentRestController(CommentCommandService commentCommandService,JWTService jwtService) {
        this.commentCommandService = commentCommandService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Create a comment", description = "Add a new comment to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment object created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input", content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest body, Authentication authentication) {
        Long userId = jwtService.getUserId(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.commentCommandService.create(body,userId));

    }

}
