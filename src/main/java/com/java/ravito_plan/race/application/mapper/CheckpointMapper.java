package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.command.CheckpointCommand;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointType;

public class CheckpointMapper {

    public static Checkpoint toCheckpoint(CheckpointCommand command) {
        return new Checkpoint(command.getName(), command.getDistanceFromStart(),
                command.getLocation(), CheckpointType.valueOf(command.getType()),
                command.getCumulatedElevationGainFromStart(),
                command.getCumulatedElevationLossFromStart(),
                command.getEstimatedTimeInMinuteFromStart(), command.getCarbsTarget());
    }
}
