package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.command.AddFoodCommand;
import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.command.DeleteFoodCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.application.mapper.view.CheckpointViewMapper;
import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.ports.UserPort;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckpointService extends BaseApplicationService {

    private final CheckpointRepository checkpointRepository;
    private final FoodPort foodPort;

    public CheckpointService(RaceRepository raceRepository, UserPort userPort,
            CheckpointRepository checkpointRepository, FoodPort foodPort) {
        super(raceRepository, userPort);
        this.checkpointRepository = checkpointRepository;
        this.foodPort = foodPort;
    }

    @Transactional
public void removeFoodFromCheckpoint(DeleteFoodCommand deleteFoodCommand) {
        this.verifyUserOwnsRace(deleteFoodCommand.getRaceId());
        Checkpoint checkpoint = this.checkpointRepository.findByIdAndRaceId(
                deleteFoodCommand.getCheckpointId(), deleteFoodCommand.getRaceId());

        checkpoint.removeFood(deleteFoodCommand.getQuantity(), deleteFoodCommand.getFoodId());
        this.checkpointRepository.save(checkpoint);
    }
}
