package com.openclassrooms.mdd.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    private Long id;
    private Long articleId;
    private Long ownerId;
    private String message;
}
