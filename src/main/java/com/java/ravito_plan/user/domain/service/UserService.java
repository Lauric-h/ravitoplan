package com.java.ravito_plan.user.domain.service;

import com.java.ravito_plan.user.application.dto.UserDto;
import com.java.ravito_plan.user.application.dto.auth.RegisterUserCommand;

public interface UserService {

    UserDto registerUser(RegisterUserCommand registerUserCommand);
    UserDto getByUsername(String username);
    boolean existsByUsername(String username);
}
