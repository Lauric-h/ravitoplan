package com.java.ravito_plan.rest.view.auth;

import jakarta.validation.constraints.NotBlank;

public class RegisterUserRequest {
    @NotBlank
    public String username;
    @NotBlank
    public String email;
    @NotBlank
    public String password;
}
