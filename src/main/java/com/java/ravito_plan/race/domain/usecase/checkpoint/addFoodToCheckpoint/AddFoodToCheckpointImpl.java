package com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddFoodToCheckpointImpl implements AddFoodToCheckpoint {

    private final RaceRepository raceRepository;
    private final CheckpointRepository checkpointRepository;
    private final FoodPort foodPort;

    public AddFoodToCheckpointImpl(RaceRepository raceRepository,
            CheckpointRepository checkpointRepository, FoodPort foodPort) {
        this.raceRepository = raceRepository;
        this.checkpointRepository = checkpointRepository;
        this.foodPort = foodPort;
    }

    @Override
    @Transactional
    public void execute(AddFoodToCheckpointRequest request,
            AddFoodToCheckpointPresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());

        Checkpoint checkpoint = this.checkpointRepository.findByIdAndRaceId(request.checkpointId(),
                request.raceId());

        RaceFoodDto externalFoodDto = this.foodPort.getFoodById(request.foodId());

        if (request.quantity() <0) {
            // Throw
        }

        Optional<CheckpointFood> existingCheckpointFood = checkpoint.findCheckpointFood(
                externalFoodDto.id());
        if (existingCheckpointFood.isPresent()) {
            checkpoint.updateCheckpointFood(existingCheckpointFood.get(), request.quantity());
        } else {
            checkpoint.addFood(
                    new CheckpointFood(checkpoint, request.quantity(), externalFoodDto.id()));
        }

        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(
                savedCheckpoint.getRace().getAllFoodIds());

        presenter.present(new AddFoodToCheckpointResponse(savedCheckpoint, foods));
    }
}
