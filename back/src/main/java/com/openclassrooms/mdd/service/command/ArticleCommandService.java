package com.openclassrooms.mdd.service.command;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.ArticleRequest;
import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.mapper.ArticleMapper;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.ThemeRepository;

/**
 * Service for handling article-related writable operations.
 * <p>
 * This service provides functionality to create articles while ensuring
 * that the associated theme exists before saving the article.
 * </p>
 */
@Service
public class ArticleCommandService {

    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;
    private final ArticleMapper articleMapper;

    /**
     * Constructs an instance of {@code ArticleCommandService} with required
     * dependencies.
     *
     * @param articleRepository the repository for managing articles.
     * @param themeRepository   the repository for validating the existence of
     *                          themes.
     * @param articleMapper     the mapper for converting between DTOs and entities.
     */
    public ArticleCommandService(ArticleRepository articleRepository,
            ThemeRepository themeRepository,
            ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
        this.articleMapper = articleMapper;
    }

    /**
     * Creates a new article.
     * <p>
     * Before saving, this method checks whether the specified theme exists.
     * If the theme is not found, a {@link NoSuchElementException} is thrown.
     * </p>
     *
     * @param request the article request containing name, description, and theme
     *                ID.
     * @param ownerId the ID of the user creating the article.
     * @return an {@link ArticleResponse} representing the newly created article.
     * @throws NoSuchElementException if the specified theme does not exist.
     */
    public ArticleResponse create(ArticleRequest request, Long ownerId) {
        themeRepository.findById(request.getThemeId())
                .orElseThrow(() -> new NoSuchElementException("Theme not found with ID: " + request.getThemeId()));
        ArticleEntity created = articleRepository.save(articleMapper.toEntity(request, ownerId));
        return articleMapper.toDto(created);
    }
}
