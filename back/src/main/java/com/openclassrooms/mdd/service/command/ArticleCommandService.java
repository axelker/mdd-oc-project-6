package com.openclassrooms.mdd.service.command;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.ArticleRequest;
import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.mapper.ArticleMapper;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.ThemeRepository;

@Service
public class ArticleCommandService {
    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;
    private final ArticleMapper articleMapper;

    ArticleCommandService(ArticleRepository articleRepository,ThemeRepository themeRepository,ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleResponse create(ArticleRequest request,Long ownerId) {
        themeRepository.findById(request.getThemeId())
                    .orElseThrow(() -> new NoSuchElementException("Theme not found with id: " + request.getThemeId()));

        ArticleEntity created = articleRepository.save(articleMapper.toEntity(request, ownerId));
        return articleMapper.toDto(created);
    }
}
