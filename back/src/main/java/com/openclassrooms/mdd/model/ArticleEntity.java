package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "articles")
public class ArticleEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255,nullable = false)
    private String name;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(nullable = false)
    private Long owner_id;

    @Column(nullable = false)
    private Long theme_id;
}