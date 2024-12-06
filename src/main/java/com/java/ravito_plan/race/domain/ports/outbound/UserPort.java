package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.user.application.dto.UserDto;

public interface UserPort {

    ExternalUserDto getUserById(Long userId);
}
