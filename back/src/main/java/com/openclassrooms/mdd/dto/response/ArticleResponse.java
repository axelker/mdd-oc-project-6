package com.openclassrooms.mdd.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleResponse {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private Long themeId;
    private LocalDateTime createdAt;
}
