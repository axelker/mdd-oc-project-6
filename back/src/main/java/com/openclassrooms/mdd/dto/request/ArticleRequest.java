package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO representing an article creation request.
 * <p>
 * This class is used to transfer article data from client requests.
 * It includes validation annotations to ensure data integrity.
 * </p>
 */
@Getter
@Builder
public class ArticleRequest {

    /**
     * The name of the article.
     * <p>
     * Must not be blank and cannot exceed 255 characters.
     * </p>
     */
    @NotBlank
    @Size(max = 255, message = "Article name must be at max 255 characters long.")
    private String name;

    /**
     * The description of the article.
     * <p>
     * Must not be blank and cannot exceed 2000 characters.
     * </p>
     */
    @NotBlank
    @Size(max = 2000, message = "Article description must be at max 2000 characters long.")
    private String description;

    /**
     * The ID of the theme associated with the article.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @NotNull(message = "ThemeId is required.")
    private Long themeId;
}
