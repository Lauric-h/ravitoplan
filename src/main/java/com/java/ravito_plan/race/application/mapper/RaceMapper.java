package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.domain.dto.RaceParams;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRaceRequest;

public class RaceMapper {

    public static CreateRaceRequest toCreateRaceRequest(Long userId, CreateRaceCommand command) {
        return new CreateRaceRequest(userId,
                new RaceParams(command.getName(), command.getDate(), command.getDistance(),
                        command.getElevationPositive(), command.getElevationNegative(),
                        command.getCity(), command.getPostalCode()));
    }

    public static UpdateRaceRequest toUpdateRaceRequest(Long userId, Long raceId,
            UpdateRaceCommand command) {
        return new UpdateRaceRequest(userId, raceId,
                new RaceParams(command.getName(), command.getDate(), command.getDistance(),
                        command.getElevationPositive(), command.getElevationNegative(),
                        command.getCity(), command.getPostalCode()));
    }
}
