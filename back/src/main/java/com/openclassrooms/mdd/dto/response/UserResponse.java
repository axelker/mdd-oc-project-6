package com.openclassrooms.mdd.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
	private Long id;
	private String name;
	private String email;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
