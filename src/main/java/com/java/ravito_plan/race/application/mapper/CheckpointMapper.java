package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.CheckpointDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;

public class CheckpointMapper {

    public static CheckpointDto toCheckpointDto(Checkpoint checkpoint) {
        return new CheckpointDto(checkpoint.getName(), checkpoint.getDistanceFromStart(),
                checkpoint.getLocation(), checkpoint.getType(),
                checkpoint.getEstimatedTimeInMinuteFromStart(),
                checkpoint.getCumulatedElevationGainFromStart(),
                checkpoint.getCumulatedElevationLossFromStart(), checkpoint.getCarbsTarget());
    }

    public static Checkpoint toCheckpoint(CheckpointDto checkpointDto) {
        return new Checkpoint(checkpointDto.name, checkpointDto.distanceFromStart,
                checkpointDto.location, checkpointDto.type,
                checkpointDto.cumulatedElevationGainFromStart,
                checkpointDto.cumulatedElevationLossFromStart,
                checkpointDto.estimatedTimeInMinuteFromStart, checkpointDto.carbsTarget);
    }
}
