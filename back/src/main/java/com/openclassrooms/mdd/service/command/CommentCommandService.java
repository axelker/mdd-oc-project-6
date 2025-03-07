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

/**
 * Service for handling comment-related writable operations.
 * <p>
 * This service provides functionality to create comments while ensuring
 * that the associated user and article exist before saving the comment.
 * </p>
 */
@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    /**
     * Constructs an instance of {@code CommentCommandService} with required dependencies.
     *
     * @param commentRepository the repository for managing comments.
     * @param articleRepository the repository for retrieving articles.
     * @param userRepository    the repository for retrieving users.
     * @param commentMapper     the mapper for converting between DTOs and entities.
     */
    public CommentCommandService(CommentRepository commentRepository,
                                 ArticleRepository articleRepository,
                                 UserRepository userRepository,
                                 CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Creates a new comment.
     * <p>
     * Before saving, this method checks whether the specified user and article exist.
     * If any of them are not found, a {@link NoSuchElementException} is thrown.
     * </p>
     *
     * @param comment   the comment request containing the message.
     * @param articleId the ID of the article to which the comment belongs.
     * @param userId    the ID of the user posting the comment.
     * @return a {@link CommentResponse} representing the newly created comment.
     * @throws NoSuchElementException if the specified user or article does not exist.
     */
    public CommentResponse create(CommentRequest comment, Long articleId, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with ID: " + articleId));

        CommentEntity entity = commentMapper.toEntity(comment)
                .toBuilder()
                .user(user)
                .article(article)
                .build();

        return commentMapper.toDto(commentRepository.save(entity));
    }
}
