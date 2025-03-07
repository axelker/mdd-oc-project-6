package com.openclassrooms.mdd.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO representing an article response.
 * <p>
 * This class is used to transfer article details in API responses.
 * </p>
 */
@Getter
@Builder
public class ArticleResponse {

    /**
     * The unique identifier of the article.
     */
    private Long id;

    /**
     * The title or name of the article.
     */
    private String name;

    /**
     * The content description of the article.
     */
    private String description;

    /**
     * The owner (author) of the article.
     * <p>
     * Represented as a {@link SummaryResponse} containing minimal user details.
     * </p>
     */
    private SummaryResponse owner;

    /**
     * The theme to which the article belongs.
     * <p>
     * Represented as a {@link SummaryResponse} containing minimal theme details.
     * </p>
     */
    private SummaryResponse theme;

    /**
     * The timestamp when the article was created.
     */
    private LocalDateTime createdAt;
}
