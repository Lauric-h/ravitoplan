package com.java.ravito_plan.race.domain.service;

import com.java.ravito_plan.race.application.dto.command.AddOrDeleteFoodCommand;
import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;

public interface CheckpointService {

    public RaceDetailView addCheckpoint(CreateCheckpointCommand createCheckpointCommand);

    public RaceDetailView updateCheckpoint(UpdateCheckpointCommand updateCheckpointCommand);

    public void deleteCheckpoint(Long raceId, Long checkpointId);

    public CheckpointView addFoodToCheckpoint(AddOrDeleteFoodCommand addFoodCommand);

    public void removeFoodFromCheckpoint(AddOrDeleteFoodCommand deleteFoodCommand);
}
