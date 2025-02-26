package com.openclassrooms.mdd.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ThemeResponse {
    private Long id;

    private String name;

    private String description;
}
