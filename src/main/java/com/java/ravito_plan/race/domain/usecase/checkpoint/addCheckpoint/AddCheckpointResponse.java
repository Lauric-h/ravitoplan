package com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Race;
import java.util.Map;

public record AddCheckpointResponse(Race race, Map<Long, RaceFoodDto> foods) {

}
