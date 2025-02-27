package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.dto.response.ArticlesResponse;
import com.openclassrooms.mdd.mapper.ArticleMapper;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;

import org.springframework.data.domain.Sort;

@Service
public class ArticleQueryService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final SubscriptionQueryService subscriptionQueryService;

    ArticleQueryService(ArticleRepository articleRepository,ArticleMapper articleMapper,SubscriptionQueryService subscriptionQueryService) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    public ArticlesResponse findAllSubscribedByUser(boolean desc,Long userId) {
        List<Long> userSubThemeIds = subscriptionQueryService.getThemeIdsUserSub(userId);

        if (userSubThemeIds.isEmpty()) {
            return ArticlesResponse.builder().articles(List.of()).build();
        }
        
        Sort sort = desc ? Sort.by(Sort.Direction.DESC, "createdAt") : Sort.by(Sort.Direction.ASC, "createdAt");

        List<ArticleResponse> articles = articleRepository.findAllByThemeIdIn(userSubThemeIds,sort).stream()
                .map(articleMapper::toDto)
                .toList();
        return ArticlesResponse.builder().articles(articles).build();
    }

    public ArticleResponse findById(Long id) {
         ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + id));
        return articleMapper.toDto(article);
    }
    
}
