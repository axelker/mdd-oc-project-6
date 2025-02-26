package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequest {

    @NotNull(message = "message is required.")
    @Size(max = 2000)
    private String message;
    @NotNull(message = "user_id is required.")
    private Long user_id;
    @NotNull(message = "article_id is required.")
    private Long article_id;
}
