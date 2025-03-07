package com.openclassrooms.mdd.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * DTO representing an theme response.
 * <p>
 * This class is used to transfer theme details in API responses.
 * </p>
 */
@Getter
@Builder
public class ThemeResponse {
    /**
     * The unique identifier of the theme.
     */
    private Long id;

    /**
     * The name of the theme.
     */
    private String name;
    /**
     * The sub status of the theme.
     */
    private boolean isSubscribed;
    /**
     * The content description of the theme.
     */
    private String description;
}
