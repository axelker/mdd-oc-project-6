package com.openclassrooms.mdd.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RentalResponse {
    private Long id;
    private String name;
    private double surface;
    private double price;
    private String[] picture;
    private String description;
    private Long owner_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
