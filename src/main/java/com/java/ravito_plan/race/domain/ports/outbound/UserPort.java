package com.java.ravito_plan.race.domain.ports.outbound;


import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;

public interface UserPort {

    RaceUserDto getByUsername(String username);
}
