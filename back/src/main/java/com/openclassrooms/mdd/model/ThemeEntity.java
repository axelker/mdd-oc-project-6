package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity representing a theme in the system.
 * <p>
 * A theme is a category to which articles and user subscriptions belong.
 * This entity extends {@link Auditable}, which manages the creation and update
 * timestamps.
 * </p>
 */
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "themes")
public class ThemeEntity extends Auditable {

    /**
     * The unique identifier of the theme.
     * <p>
     * Generated automatically by the database using an identity strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the theme.
     * <p>
     * Cannot be null and has a maximum length of 255 characters.
     * </p>
     */
    @Column(length = 255, nullable = false)
    private String name;

    /**
     * A brief description of the theme.
     * <p>
     * Cannot be null and has a maximum length of 2000 characters.
     * </p>
     */
    @Column(length = 2000, nullable = false)
    private String description;
}
