package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.command.AddFoodCommand;
import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.command.DeleteFoodCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.application.mapper.view.CheckpointViewMapper;
import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.inbound.CheckpointPort;
import com.java.ravito_plan.race.domain.ports.outbound.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.outbound.FoodPort;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckpointService extends BaseApplicationService implements CheckpointPort {

    private final CheckpointRepository checkpointRepository;
    private final FoodPort foodPort;

    public CheckpointService(RaceRepository raceRepository, UserPort userPort,
            CheckpointRepository checkpointRepository, FoodPort foodPort) {
        super(raceRepository, userPort);
        this.checkpointRepository = checkpointRepository;
        this.foodPort = foodPort;
    }

    @Transactional
    public RaceDetailView addCheckpoint(CreateCheckpointCommand createCheckpointCommand) {
        RaceUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(createCheckpointCommand.getRaceId(),
                user.id);
        race.addOrUpdateCheckpoint(CheckpointMapper.toCheckpoint(createCheckpointCommand));

        Race updatedRace = this.raceRepository.save(race);
        Map<Long, FoodDto> foods = this.foodPort.getFoodsByIds(
                this.getAllFoodIdsForRace(updatedRace));
        return RaceViewMapper.toRaceDetailView(updatedRace, foods);
    }

    @Transactional
    public RaceDetailView updateCheckpoint(UpdateCheckpointCommand updateCheckpointCommand) {
        this.verifyUserOwnsRace(updateCheckpointCommand.getRaceId());

        Checkpoint checkpoint = this.checkpointRepository.findByIdAndRaceId(
                updateCheckpointCommand.getCheckpointId(), updateCheckpointCommand.getRaceId());

        checkpoint.updateDetails(CheckpointMapper.toCheckpoint(updateCheckpointCommand));

        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);

        Map<Long, FoodDto> foods = this.foodPort.getFoodsByIds(
                this.getAllFoodIdsForRace(savedCheckpoint.getRace()));

        return RaceViewMapper.toRaceDetailView(savedCheckpoint.getRace(), foods);
    }

    @Transactional
    public void deleteCheckpoint(Long raceId, Long checkpointId) {
        this.verifyUserOwnsRace(raceId);

        this.checkpointRepository.findByIdAndRaceId(checkpointId, raceId);

        this.checkpointRepository.deleteById(checkpointId);
    }

    @Transactional
    public CheckpointView addFoodToCheckpoint(AddFoodCommand addFoodCommand) {
        this.verifyUserOwnsRace(addFoodCommand.getRaceId());
        Checkpoint checkpoint = this.checkpointRepository.findByIdAndRaceId(
                addFoodCommand.getCheckpointId(), addFoodCommand.getRaceId());

        FoodDto externalFoodDto = this.foodPort.getFoodById(addFoodCommand.getFoodId());
        checkpoint.addFood(addFoodCommand.getQuantity(), externalFoodDto.id);

        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);
        Map<Long, FoodDto> foods = this.foodPort.getFoodsByIds(
                this.getAllFoodIdsForRace(savedCheckpoint.getRace()));

        return CheckpointViewMapper.toCheckpointDetailView(savedCheckpoint, foods);
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
