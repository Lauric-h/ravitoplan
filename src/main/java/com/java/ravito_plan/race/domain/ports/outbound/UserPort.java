package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.application.dto.internal.UserDto;

public interface UserPort {

    UserDto getByUsername(String username);
}
