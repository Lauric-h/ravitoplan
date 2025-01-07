package com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointFoodRepository;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateFoodQuantityImpl implements UpdateFoodQuantity {

    private final RaceRepository raceRepository;
    private final CheckpointRepository checkpointRepository;
    private final CheckpointFoodRepository checkpointFoodRepository;
    private final FoodPort foodPort;

    public UpdateFoodQuantityImpl(RaceRepository raceRepository,
            CheckpointRepository checkpointRepository,
            CheckpointFoodRepository checkpointFoodRepository, FoodPort foodPort) {
        this.raceRepository = raceRepository;
        this.checkpointRepository = checkpointRepository;
        this.checkpointFoodRepository = checkpointFoodRepository;
        this.foodPort = foodPort;
    }


    @Override
    @Transactional
    public void execute(UpdateFoodQuantityRequest request, UpdateFoodQuantityPresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());

        CheckpointFood checkpointFood = this.checkpointFoodRepository.findByFoodIdAndCheckpointId(
                request.foodId(), request.checkpointId());
        checkpointFood.setQuantity(request.quantity());
        checkpointFood.getCheckpoint().updateFoodQuantity(checkpointFood);

        Checkpoint updatedCheckpoint = this.checkpointRepository.save(
                checkpointFood.getCheckpoint());

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(
                updatedCheckpoint.getRace().getAllFoodIds());

        presenter.present(new UpdateFoodQuantityResponse(checkpointFood.getCheckpoint(), foods));
    }
}
