package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.dto.response.Response;
import com.openclassrooms.mdd.dto.response.ThemeResponse;
import com.openclassrooms.mdd.dto.response.ThemesResponse;
import com.openclassrooms.mdd.service.auth.JWTService;
import com.openclassrooms.mdd.service.command.CommentCommandService;
import com.openclassrooms.mdd.service.command.SubscriptionCommandService;
import com.openclassrooms.mdd.service.query.ThemeQueryService;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Themes", description = "Manage themes and theme subscription.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/themes")
public class ThemeController {

        private final ThemeQueryService themeQueryService;
    private final SubscriptionCommandService subscriptionCommandService;
    private final JWTService jwtService;

    public ThemeController(ThemeQueryService themeQueryService,SubscriptionCommandService subscriptionCommandService, JWTService jwtService) {
        this.themeQueryService = themeQueryService;
        this.subscriptionCommandService = subscriptionCommandService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Get all themes", description = "Retrieve all available themes.")
    @GetMapping
    public ResponseEntity<ThemesResponse> getAllThemes() {
        return ResponseEntity.ok(themeQueryService.findAll());
    }

    @Operation(summary = "Create a subscription", description = "Add a new subscription for the theme provided to the system.")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "201", description = "Subscription object created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request - Invalid theme ID or user already subscribed", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found - Theme does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("{id}/subscribe")
    public ResponseEntity<Response> create(@PathVariable Long id,
            Authentication authentication) {
        Long userId = jwtService.getUserId(authentication);
        this.subscriptionCommandService.subscribe(id, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.builder().message("Subscribe successfully !").build());

    }

    @Operation(summary = "Delete a subscription", description = "Unsubscribe a user from a theme by deleting the subscription. "
            + "The user must be authenticated and can only delete their own subscription.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully unsubscribed", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid subscription ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found - Subscription does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("{id}/unsubscribe")
    public ResponseEntity<Response> delete( @PathVariable Long id, Authentication authentication) {
        Long userId = jwtService.getUserId(authentication);
        this.subscriptionCommandService.unSubscribe(id, userId);
        return ResponseEntity.ok(Response.builder().message("Unsubscribe successfully !").build());

    }
}
