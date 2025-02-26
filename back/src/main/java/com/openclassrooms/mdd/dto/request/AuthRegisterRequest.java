package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRegisterRequest {
    @NotNull(message = "Name is required.")
    private String name;
    @NotNull(message = "Email is required.")
    @Email
    private String email;
    @NotNull(message = "Password is required.")
    private String password;
}
