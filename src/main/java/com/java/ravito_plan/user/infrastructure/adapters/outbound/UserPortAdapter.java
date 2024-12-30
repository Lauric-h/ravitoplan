package com.java.ravito_plan.user.infrastructure.adapters.outbound;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
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
    public RaceUserDto getByUsername(String username) {
        UserDto user = this.userApplicationService.getByUsername(username);

        return new RaceUserDto(user.id, user.username);
    }
}
