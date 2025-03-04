package com.openclassrooms.mdd.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummaryResponse {
    private Long id;
    private String name;
}
