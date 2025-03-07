package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity representing a user's subscription to a theme.
 * <p>
 * This entity extends {@link Auditable}, which manages the creation and update timestamps.
 * Each subscription links a {@link UserEntity} to a {@link ThemeEntity}.
 * </p>
 */
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "subscriptions")
public class SubscriptionEntity extends Auditable {

    /**
     * The unique identifier of the subscription.
     * <p>
     * Generated automatically by the database using an identity strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who subscribed to a theme.
     * <p>
     * This is a many-to-one relationship with {@link UserEntity}, meaning multiple
     * subscriptions can be associated with a single user. The foreign key is {@code user_id}.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * The theme to which the user is subscribed.
     * <p>
     * This is a many-to-one relationship with {@link ThemeEntity}, meaning multiple
     * users can subscribe to a single theme. The foreign key is {@code theme_id}.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private ThemeEntity theme;
}
