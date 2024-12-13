package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;

public interface UserPort {

    ExternalUserDto getByUsername(String username);
}
