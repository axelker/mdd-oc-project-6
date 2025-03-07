package com.openclassrooms.mdd.service.query;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.mapper.CommentMapper;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.CommentRepository;

/**
 * Service for querying comment data.
 * <p>
 * This service provides functionality for retrieving comments associated with a specific article.
 * </p>
 */
@Service
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    /**
     * Constructs an instance of {@code CommentQueryService} with the required repositories and mapper.
     *
     * @param commentRepository the repository for managing comments.
     * @param articleRepository the repository for managing articles.
     * @param commentMapper     the mapper for converting comment entities to DTOs.
     */
    public CommentQueryService(CommentRepository commentRepository,
                                ArticleRepository articleRepository,
                                CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Retrieves all comments for a specific article.
     * <p>
     * This method verifies if the article exists in the repository by its ID. If the article is found,
     * it fetches all the comments associated with that article. If the article is not found, a
     * {@link NoSuchElementException} is thrown.
     * </p>
     *
     * @param articleId the ID of the article to retrieve comments for.
     * @return a list of {@link CommentResponse} representing the comments for the specified article.
     * @throws NoSuchElementException if the article does not exist.
     */
    public List<CommentResponse> findAll(Long articleId) {
        articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with ID: " + articleId));

        return commentRepository.findAllByArticleId(articleId).stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
