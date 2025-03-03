package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.ThemeResponse;
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

    public ThemeResponse findById(Long id) {
        return themeRepository.findById(id)
                .map(themeMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with id: " + id));
    }
    public List<ThemeResponse> findAll() {
       return themeRepository.findAll().stream()
                .map(themeMapper::toDto)
                .toList();
    }
    
}
