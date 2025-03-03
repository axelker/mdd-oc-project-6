package com.openclassrooms.mdd.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
	private Long id;
	private String username;
	private String email;
}
