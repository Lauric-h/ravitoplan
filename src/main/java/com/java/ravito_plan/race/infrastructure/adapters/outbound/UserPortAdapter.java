package com.java.ravito_plan.race.infrastructure.adapters.outbound;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import com.java.ravito_plan.user.application.dto.UserDto;
import com.java.ravito_plan.user.application.service.UserApplicationService;
import org.springframework.stereotype.Component;

@Component
public class UserPortAdapter implements UserPort {

    private final UserApplicationService userApplicationService;

    public UserPortAdapter(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Override
    public ExternalUserDto getUserById(Long userId) {
        UserDto user = this.userApplicationService.getUserById(userId);

        return new ExternalUserDto(userId, user.username);
    }

    @Override
    public boolean userExistsById(Long userId) {
        return this.userApplicationService.userExistsById(userId);
    }
}
