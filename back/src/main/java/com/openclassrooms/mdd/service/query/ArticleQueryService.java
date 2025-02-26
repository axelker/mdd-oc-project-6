package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    ArticleQueryService(ArticleRepository articleRepository,ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public ArticlesResponse findAll(boolean desc) {
        Sort sort = desc ? Sort.by(Sort.Direction.DESC, "createdAt") : Sort.by(Sort.Direction.ASC, "createdAt");

        List<ArticleResponse> articles = articleRepository.findAll(sort).stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
        return ArticlesResponse.builder().articles(articles).build();
    }

    public ArticleResponse findById(Long id) {
         ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + id));
        return articleMapper.toDto(article);
    }
    
}
