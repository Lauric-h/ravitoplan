package com.java.ravito_plan.user.domain.ports.inbound;

import com.java.ravito_plan.user.application.dto.UserDto;
import com.java.ravito_plan.user.application.dto.auth.RegisterUserCommand;

public interface UserPort {

    UserDto registerUser(RegisterUserCommand registerUserCommand);
    UserDto getByUsername(String username);
    boolean existsByUsername(String username);
}
