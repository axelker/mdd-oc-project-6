
package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "themes")
public class ThemeEntity extends Auditable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255,nullable=false)
    private String name;

    @Column(length = 2000,nullable=false)
    private String description;
}
