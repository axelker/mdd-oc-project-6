package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity representing an article in the system.
 * <p>
 * This entity extends {@link Auditable}, which manages the creation and update
 * timestamps.
 * </p>
 */
@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "articles")
public class ArticleEntity extends Auditable {

    /**
     * The unique identifier of the article.
     * <p>
     * Generated automatically by the database using an identity strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title or name of the article.
     * <p>
     * Cannot be null and has a maximum length of 255 characters.
     * </p>
     */
    @Column(length = 255, nullable = false)
    private String name;

    /**
     * The content description of the article.
     * <p>
     * Cannot be null and has a maximum length of 2000 characters.
     * </p>
     */
    @Column(length = 2000, nullable = false)
    private String description;

    /**
     * The owner (author) of the article.
     * <p>
     * This is a many-to-one relationship with {@link UserEntity}. It is lazily
     * loaded
     * and mapped using the foreign key {@code owner_id}.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    /**
     * The theme associated with the article.
     * <p>
     * This is a many-to-one relationship with {@link ThemeEntity}. It is lazily
     * loaded
     * and mapped using the foreign key {@code theme_id}.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private ThemeEntity theme;
}
