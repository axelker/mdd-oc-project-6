package com.openclassrooms.mdd.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.ArticleRequest;
import com.openclassrooms.mdd.dto.response.ArticleResponse;
import com.openclassrooms.mdd.mapper.ArticleMapper;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;

@Service
public class ArticleCommandService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    ArticleCommandService(ArticleRepository articleRepository,ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleResponse create(ArticleRequest request,Long ownerId) {
        ArticleEntity created = articleRepository.save(articleMapper.toEntity(request, ownerId));
        return articleMapper.toDto(created);
    }
}
