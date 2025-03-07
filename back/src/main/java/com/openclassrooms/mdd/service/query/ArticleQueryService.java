package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.mapper.ArticleMapper;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;

/**
 * Service for querying article data.
 * <p>
 * This service provides functionality to retrieve articles based on user subscriptions
 * and to fetch articles by their ID.
 * </p>
 */
@Service
public class ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final SubscriptionQueryService subscriptionQueryService;

    /**
     * Constructs an instance of {@code ArticleQueryService} with required dependencies.
     *
     * @param articleRepository the repository for managing articles.
     * @param articleMapper     the mapper for converting article entities to DTOs.
     * @param subscriptionQueryService the service for managing user subscriptions.
     */
    ArticleQueryService(ArticleRepository articleRepository,
                        ArticleMapper articleMapper,
                        SubscriptionQueryService subscriptionQueryService) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    /**
     * Retrieves all articles that the user is subscribed to, sorted by creation date.
     * <p>
     * This method first checks the themes the user is subscribed to, then fetches all articles
     * from those themes, sorted by the creation date in either ascending or descending order.
     * </p>
     *
     * @param desc    if {@code true}, the articles will be sorted in descending order by creation date;
     *                if {@code false}, the articles will be sorted in ascending order.
     * @param userId  the ID of the user whose subscribed articles to retrieve.
     * @return a list of {@link ArticleResponse} representing the articles the user is subscribed to.
     */
    public List<ArticleResponse> findAllSubscribedByUser(boolean desc, Long userId) {
        List<Long> userSubThemeIds = subscriptionQueryService.getThemeIdsUserSub(userId);

        if (userSubThemeIds.isEmpty()) {
            return List.of();
        }

        Sort sort = desc ? Sort.by(Sort.Direction.DESC, "createdAt") : Sort.by(Sort.Direction.ASC, "createdAt");

        return articleRepository.findAllByThemeIdIn(userSubThemeIds, sort).stream()
                .map(articleMapper::toDto)
                .toList();
    }

    /**
     * Retrieves an article by its ID.
     * <p>
     * This method fetches the article from the repository using the provided ID. If the article is not found,
     * a {@link NoSuchElementException} is thrown.
     * </p>
     *
     * @param id the ID of the article to retrieve.
     * @return an {@link ArticleResponse} representing the article.
     * @throws NoSuchElementException if the article with the provided ID does not exist.
     */
    public ArticleResponse findById(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found with ID: " + id));

        return articleMapper.toDto(article);
    }
}
