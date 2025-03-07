package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.mapper.CommentMapper;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.CommentRepository;

@Service
public class CommentQueryService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    public CommentQueryService(CommentRepository commentRepository, ArticleRepository articleRepository,
            CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentResponse> findAll(Long articleId) {

        articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + articleId));

        return commentRepository.findAllByArticleId(articleId).stream().map(commentMapper::toDto).toList();
    }
}
