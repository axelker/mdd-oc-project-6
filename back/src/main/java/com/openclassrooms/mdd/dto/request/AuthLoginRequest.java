package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthLoginRequest {

    @NotNull(message = "Email or username is required.")
    private String identifier;

    @NotNull(message="password is required")
    private String password;
}