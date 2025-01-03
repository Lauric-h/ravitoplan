package com.java.ravito_plan.race.domain.usecase.race.createRace;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;

public record CreateRaceRequest(Long userId, CreateRaceCommand command) {

}
