package com.openclassrooms.mdd.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {

    @NotNull(message = "Username is required.")
    private String username;

    @NotNull(message = "Email is required.")
    @Email
    private String email;


}
