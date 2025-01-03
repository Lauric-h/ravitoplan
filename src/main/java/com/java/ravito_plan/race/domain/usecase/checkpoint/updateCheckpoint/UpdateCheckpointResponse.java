package com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Race;
import java.util.Map;

public record UpdateCheckpointResponse(Race race, Map<Long, RaceFoodDto> foods) {

}
