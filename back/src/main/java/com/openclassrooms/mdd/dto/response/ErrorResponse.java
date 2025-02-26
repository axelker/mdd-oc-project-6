package com.openclassrooms.mdd.dto.response;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Standard error response structure")
public class ErrorResponse {

    @Schema(description = "Timestamp when the error occurred", example = "2021-01-01T23:30:00")
    private final LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "5XX")
    private final int status;

    @Schema(description = "Error message", example = "Invalid request parameters")
    private final String message;

    @Schema(description = "Path of the requested endpoint", example = "/api/5")
    private final String path;

}