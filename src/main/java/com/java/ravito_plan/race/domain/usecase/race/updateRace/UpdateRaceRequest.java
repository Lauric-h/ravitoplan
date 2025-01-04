package com.java.ravito_plan.race.domain.usecase.race.updateRace;

import com.java.ravito_plan.race.domain.dto.RaceParams;

public record UpdateRaceRequest(Long userId, Long raceId, RaceParams raceParams) {

}
