package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleRequest {

    @NotBlank
    @Size(max = 255, message = "Article name must be at max 255 characters long.")
    private String name;

    @NotBlank
    @Size(max = 2000, message = "Article descritpion must be at max 2000 characters long.")
    private String description;

    @NotNull
    private Long themeId;
}
