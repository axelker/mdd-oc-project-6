package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO representing a comment request.
 * <p>
 * This class is used to transfer comment data from client requests.
 * It includes validation annotations to ensure data integrity.
 * </p>
 */
@Getter
@Builder
public class CommentRequest {
    /**
     * The message content of the comment.
     * <p>
     * This field is required and must not exceed 2000 characters.
     * </p>
     */
    @NotNull(message = "message is required.")
    @Size(max = 2000)
    private String message;

}
