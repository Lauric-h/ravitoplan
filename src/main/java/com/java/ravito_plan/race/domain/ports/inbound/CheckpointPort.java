package com.java.ravito_plan.race.domain.ports.inbound;

import com.java.ravito_plan.race.application.dto.command.AddFoodCommand;
import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.command.DeleteFoodCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;

public interface CheckpointPort {

    public RaceDetailView addCheckpoint(CreateCheckpointCommand createCheckpointCommand);

    public RaceDetailView updateCheckpoint(UpdateCheckpointCommand updateCheckpointCommand);

    public void deleteCheckpoint(Long raceId, Long checkpointId);

    public CheckpointView addFoodToCheckpoint(AddFoodCommand addFoodCommand);

    public void removeFoodFromCheckpoint(DeleteFoodCommand deleteFoodCommand);
}
