package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleRequest {

    @NotBlank
    @Max(value=255, message = "Article name must be at max 255 characters long.")
    private String name;

    @NotBlank
    @Max(value=2000, message = "Article name must be at max 2000 characters long.")
    private String description;

    @NotNull
    private Long themeId;
}
