package com.openclassrooms.mdd.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ThemesResponse {
    List<ThemeResponse> themes;
}
