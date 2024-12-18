package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.food.application.dto.FoodDto;
import com.java.ravito_plan.race.application.dto.CheckpointDto;
import com.java.ravito_plan.race.application.dto.CheckpointFoodDto;
import com.java.ravito_plan.race.application.dto.ExternalFoodDto;
import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.dto.RaceFullDto;
import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.outbound.FoodPort;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import com.java.ravito_plan.race.infrastructure.adapters.outbound.FoodPortAdapter;
import org.springframework.stereotype.Service;

@Service
public class CheckpointService extends BaseApplicationService {
    CheckpointRepository checkpointRepository;
    FoodPort foodPort;

    public CheckpointService(RaceRepository raceRepository, UserPort userPort,
            CheckpointRepository checkpointRepository, FoodPort foodPort) {
        super(raceRepository, userPort);
        this.checkpointRepository = checkpointRepository;
        this.foodPort = foodPort;
    }

    public RaceDto addCheckpoint(Long raceId, CheckpointDto checkpointDto) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        race.addOrUpdateCheckpoint(CheckpointMapper.toCheckpoint(checkpointDto));

        Race updatedRace = this.raceRepository.save(race);
        return RaceMapper.toRaceDto(updatedRace);
    }

    public RaceDto updateCheckpoint(Long raceId, Long checkpointId, CheckpointDto checkpointDto) {
        this.verifyUserOwnsRace(raceId);

        Checkpoint checkpoint =  this.checkpointRepository.findByIdAndRaceId(checkpointId, raceId);

        checkpoint.updateDetails(CheckpointMapper.toCheckpoint(checkpointDto));

        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);
        return RaceMapper.toRaceDto(savedCheckpoint.getRace());
    }

    public void deleteCheckpoint(Long raceId, Long checkpointId) {
        this.verifyUserOwnsRace(raceId);

        this.checkpointRepository.findByIdAndRaceId(checkpointId, raceId);

        this.checkpointRepository.deleteById(checkpointId);
    }

    public CheckpointDto addFoodToCheckpoint(Long raceId, CheckpointFoodDto checkpointFoodDto) {
        this.verifyUserOwnsRace(raceId);
        Checkpoint checkpoint =  this.checkpointRepository.findByIdAndRaceId(checkpointFoodDto.checkpointId, raceId);

        ExternalFoodDto externalFoodDto = this.foodPort.getFoodById(checkpointFoodDto.foodId);
        checkpoint.addFood(checkpointFoodDto.quantity, externalFoodDto.id);
        return CheckpointMapper.toCheckpointDto(this.checkpointRepository.save(checkpoint));
    }

    public void removeFoodFromCheckpoint(Long raceId, CheckpointFoodDto checkpointFoodDto) {
        this.verifyUserOwnsRace(raceId);
        Checkpoint checkpoint =  this.checkpointRepository.findByIdAndRaceId(checkpointFoodDto.checkpointId, raceId);

        // TODO
    }
}
