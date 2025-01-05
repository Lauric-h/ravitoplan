package com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.ports.CheckpointFactory;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateCheckpointImpl implements UpdateCheckpoint {

    private final CheckpointRepository checkpointRepository;
    private final RaceRepository raceRepository;
    private final FoodPort foodPort;
    private final CheckpointFactory checkpointFactory;

    public UpdateCheckpointImpl(CheckpointRepository checkpointRepository,
            RaceRepository raceRepository, FoodPort foodPort, CheckpointFactory checkpointFactory) {
        this.checkpointRepository = checkpointRepository;
        this.raceRepository = raceRepository;
        this.foodPort = foodPort;
        this.checkpointFactory = checkpointFactory;
    }

    @Override
    @Transactional
    public void execute(UpdateCheckpointRequest request, UpdateCheckpointPresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());

        Checkpoint checkpoint = checkpointRepository.findByIdAndRaceId(request.checkpointId(),
                request.raceId());
        checkpoint.updateDetails(this.checkpointFactory.create(request.checkpointParams()));

        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);

        Map<Long, RaceFoodDto> foods = this.foodPort.getFoodsByIds(
                savedCheckpoint.getRace().getAllFoodIds());

        presenter.present(new UpdateCheckpointResponse(savedCheckpoint.getRace(), foods));
    }
}
