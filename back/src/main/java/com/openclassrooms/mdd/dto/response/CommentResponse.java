package com.openclassrooms.mdd.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * DTO representing a comment response.
 * <p>
 * This class is used to transfer comment details in API responses.
 * </p>
 */
@Getter
@Builder
public class CommentResponse {

    /**
     * The unique identifier of the comment.
     */
    private Long id;

    /**
     * The owner (author) of the comment.
     * <p>
     * Represented as a {@link SummaryResponse} containing minimal user details.
     * </p>
     */
    private SummaryResponse owner;

    /**
     * The content of the comment.
     */
    private String message;
}
