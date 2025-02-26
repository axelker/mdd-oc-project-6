package com.openclassrooms.mdd.service.command;

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
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentCommandService(CommentRepository commentRepository,ArticleRepository articleRepository, UserRepository userRepository,
            CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public CommentResponse create(CommentRequest comment,Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        
        ArticleEntity article = articleRepository.findById(comment.getArticleId())
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + comment.getArticleId()));
        
        CommentEntity entity = commentMapper.toEntity(comment);
        entity = entity.toBuilder()
                .user(user)
                .article(article)
                .build();
        return commentMapper.toDto(commentRepository.save(entity));
    }
}
