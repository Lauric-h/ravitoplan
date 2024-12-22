package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.command.RaceCommand;
import com.java.ravito_plan.race.domain.model.Race;

public class RaceMapper {

    public static Race toRace(RaceCommand command) {
        return Race.create(command.getName(), command.getDate(), command.getDistance(),
                command.getElevationPositive(), command.getElevationNegative(), command.getCity(),
                command.getPostalCode());
    }
}
