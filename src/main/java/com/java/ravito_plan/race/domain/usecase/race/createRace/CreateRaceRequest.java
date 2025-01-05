package com.java.ravito_plan.race.domain.usecase.race.createRace;

import com.java.ravito_plan.race.domain.dto.RaceParams;

public record CreateRaceRequest(Long userId, RaceParams raceParams) {

}
