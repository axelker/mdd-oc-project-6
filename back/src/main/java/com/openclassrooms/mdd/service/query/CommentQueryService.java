package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.mapper.CommentMapper;
import com.openclassrooms.mdd.model.ArticleEntity;
import com.openclassrooms.mdd.model.CommentEntity;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.CommentRepository;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class CommentQueryService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentQueryService(CommentRepository commentRepository,ArticleRepository articleRepository, UserRepository userRepository,
            CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentResponse> findAll(Long articleId,Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        
        articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + articleId));
        
        return commentRepository.findAllByArticleIdAndUserId(articleId,userId).stream().map(commentMapper::toDto).toList();
    }
}
