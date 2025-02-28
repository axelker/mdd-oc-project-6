package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthLoginRequest {

    @NotNull(message = "email is required.")
    @Email
    private String email;

    @NotNull(message="password is required")
    private String password;
}