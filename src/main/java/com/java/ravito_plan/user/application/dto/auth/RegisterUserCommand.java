package com.java.ravito_plan.user.application.dto.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterUserCommand {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
