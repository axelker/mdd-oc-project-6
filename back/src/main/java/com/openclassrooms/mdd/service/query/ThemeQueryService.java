package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.ThemeResponse;
import com.openclassrooms.mdd.dto.response.ThemesResponse;
import com.openclassrooms.mdd.mapper.ThemeMapper;
import com.openclassrooms.mdd.repository.ThemeRepository;

@Service
public class ThemeQueryService {
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    ThemeQueryService(ThemeRepository themeRepository,ThemeMapper themeMapper) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }

    public ThemesResponse findAll() {
       List<ThemeResponse> themes = themeRepository.findAll().stream()
                .map(themeMapper::toDto)
                .collect(Collectors.toList());
        return ThemesResponse.builder().themes(themes).build();
    }
    
}
