package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity representing a comment in the system.
 * <p>
 * This entity extends {@link Auditable}, which manages the creation and update timestamps.
 * Each comment is linked to a {@link UserEntity} (author) and an {@link ArticleEntity}.
 * </p>
 */
@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "comments")
public class CommentEntity extends Auditable {

    /**
     * The unique identifier of the comment.
     * <p>
     * Generated automatically by the database using an identity strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The message content of the comment.
     * <p>
     * Cannot be null and has a maximum length of 2000 characters.
     * </p>
     */
    @Column(length = 2000, nullable = false)
    private String message;

    /**
     * The user who posted the comment.
     * <p>
     * This is a many-to-one relationship with {@link UserEntity}, meaning multiple comments
     * can be associated with a single user. The foreign key is {@code user_id}.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * The article to which the comment belongs.
     * <p>
     * This is a many-to-one relationship with {@link ArticleEntity}, meaning multiple comments
     * can be linked to a single article. The foreign key is {@code article_id}.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;
}
