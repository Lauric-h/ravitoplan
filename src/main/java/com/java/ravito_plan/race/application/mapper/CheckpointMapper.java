package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.domain.dto.CheckpointParams;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint.AddCheckpointRequest;
import com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint.UpdateCheckpointRequest;

public class CheckpointMapper {

    public static AddCheckpointRequest toAddCheckpointRequest(Long userId, Long raceId,
            CreateCheckpointCommand command) {
        return new AddCheckpointRequest(userId, raceId,
                new CheckpointParams(command.getName(), command.getDistanceFromStart(),
                        command.getLocation(), CheckpointType.valueOf(command.getType()),
                        command.getEstimatedTimeInMinuteFromStart(),
                        command.getCumulatedElevationGainFromStart(),
                        command.getCumulatedElevationLossFromStart(), command.getCarbsTarget()));
    }

    public static UpdateCheckpointRequest toUpdateCheckpointRequest(Long userId, Long raceId,
            Long checkpointId, UpdateCheckpointCommand command) {
        return new UpdateCheckpointRequest(raceId, checkpointId, userId,
                new CheckpointParams(command.getName(), command.getDistanceFromStart(),
                        command.getLocation(), CheckpointType.valueOf(command.getType()),
                        command.getEstimatedTimeInMinuteFromStart(),
                        command.getCumulatedElevationGainFromStart(),
                        command.getCumulatedElevationLossFromStart(), command.getCarbsTarget()));
    }
}
