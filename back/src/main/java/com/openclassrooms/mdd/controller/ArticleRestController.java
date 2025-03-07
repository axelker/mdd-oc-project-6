package com.openclassrooms.mdd.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.dto.request.ArticleRequest;
import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.dto.response.ErrorResponse;
import com.openclassrooms.mdd.service.auth.JWTService;
import com.openclassrooms.mdd.service.command.ArticleCommandService;
import com.openclassrooms.mdd.service.command.CommentCommandService;
import com.openclassrooms.mdd.service.query.ArticleQueryService;
import com.openclassrooms.mdd.service.query.CommentQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * REST controller for managing articles and comments on article.
 * <p>
 * Provides endpoints to create, retrieve, and interact with articles and
 * comments.
 * Users must be authenticated.
 * </p>
 */
@Tag(name = "Articles", description = "Manage Articles.")
@ApiResponses(value = {
                @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/articles")
public class ArticleRestController {

        private final ArticleQueryService articleQueryService;
        private final ArticleCommandService articleCommandService;
        private final CommentCommandService commentCommandService;
        private final CommentQueryService commentQueryService;
        private final JWTService jwtService;

        /**
         * Constructs the ArticleRestController with required services.
         *
         * @param articleQueryService   service for querying articles.
         * @param articleCommandService service for handling article creation and
         *                              updates.
         * @param commentCommandService service for handling comment creation.
         * @param commentQueryService   service for querying comments.
         * @param jwtService            service for JWT token processing.
         */
        public ArticleRestController(ArticleQueryService articleQueryService,
                        ArticleCommandService articleCommandService,
                        CommentCommandService commentCommandService, CommentQueryService commentQueryService,
                        JWTService jwtService) {
                this.articleQueryService = articleQueryService;
                this.articleCommandService = articleCommandService;
                this.commentCommandService = commentCommandService;
                this.commentQueryService = commentQueryService;
                this.jwtService = jwtService;
        }

        /**
         * Retrieves an article by its ID.
         *
         * @param id the ID of the article to retrieve.
         * @return the requested article wrapped in a {@link ResponseEntity}.
         */
        @Operation(summary = "Get an article by ID", description = "Retrieves the article with the specified ID. The user does not need to be authenticated.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Article found successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request - Invalid ID format", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found - No article found with the given ID", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
        })
        @GetMapping("{id}")
        public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
                return ResponseEntity.ok(articleQueryService.findById(id));
        }

        /**
         * Retrieves all articles from followed themes for the authenticated user.
         *
         * @param desc     whether to sort results in descending order (default: true).
         * @param jwtToken the JWT token from cookies to identify the user.
         * @return a list of articles wrapped in a {@link ResponseEntity}.
         */
        @Operation(summary = "Get articles from followed themes", description = "Retrieves all articles from the themes the authenticated user is subscribed to. "
                        + "The results can be sorted by creation date.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Articles retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ArticleResponse.class)))),
                        @ApiResponse(responseCode = "400", description = "Bad Request - Invalid sorting parameter", content = @Content),
        })
        @GetMapping("")
        public ResponseEntity<List<ArticleResponse>> findAll(
                        @RequestParam(required = false, defaultValue = "true") Boolean desc,
                        @CookieValue(name = "jwt") String jwtToken) {
                Long userId = jwtService.extractUserId(jwtToken);
                return ResponseEntity.ok(articleQueryService.findAllSubscribedByUser(desc.booleanValue(), userId));
        }

        /**
         * Creates a new article.
         *
         * @param body     the request body containing article details.
         * @param jwtToken the JWT token from cookies to authenticate the user.
         * @return the created article wrapped in a {@link ResponseEntity} with HTTP 201
         *         status.
         */
        @Operation(summary = "Create an article", description = "Allows an authenticated user to create article. ")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Article created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input (empty message, too long, etc.)", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found - Theme with the given ID does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))

        })
        @PostMapping("")
        public ResponseEntity<ArticleResponse> create(@Valid @RequestBody ArticleRequest body,
                        @CookieValue(name = "jwt") String jwtToken) {
                Long userId = jwtService.extractUserId(jwtToken);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(this.articleCommandService.create(body, userId));

        }

        /**
         * Adds a comment to an article.
         *
         * @param id       the ID of the article to comment on.
         * @param body     the request body containing comment details.
         * @param jwtToken the JWT token from cookies to authenticate the user.
         * @return the created comment wrapped in a {@link ResponseEntity} with HTTP 201
         *         status.
         */
        @Operation(summary = "Create a comment on an article", description = "Allows an authenticated user to add a comment to the specified article. "
                        + "If the article does not exist, an error will be returned.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Comment created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input (empty message, too long, etc.)", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found - Article with the given ID does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PostMapping("{id}/comments")
        public ResponseEntity<CommentResponse> createComment(@PathVariable Long id,
                        @Valid @RequestBody CommentRequest body,
                        @CookieValue(name = "jwt") String jwtToken) {
                Long userId = jwtService.extractUserId(jwtToken);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(this.commentCommandService.create(body, id, userId));

        }

        /**
         * Retrieves all comments for a given article.
         *
         * @param id the ID of the article whose comments are to be retrieved.
         * @return a list of comments wrapped in a {@link ResponseEntity}.
         */
        @Operation(summary = "Get all comments on article", description = "Allows an authenticated user to view comments on an article. "
                        + "If the article does not exist, an error will be returned.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "List of comments retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CommentResponse.class)))),
                        @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input (empty message, too long, etc.)", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found - Article with the given ID does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
        })
        @GetMapping("{id}/comments")
        public ResponseEntity<List<CommentResponse>> findAllComments(@PathVariable Long id) {
                return ResponseEntity.ok(this.commentQueryService.findAll(id));

        }
}
