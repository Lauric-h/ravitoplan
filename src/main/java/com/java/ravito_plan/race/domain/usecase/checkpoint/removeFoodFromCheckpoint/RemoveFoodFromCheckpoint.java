package com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint;

import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RemoveFoodFromCheckpoint implements RemoveFoodFromCheckpointInterface {

    private final CheckpointRepository checkpointRepository;
    private final RaceRepository raceRepository;

    public RemoveFoodFromCheckpoint(CheckpointRepository checkpointRepository,
            RaceRepository raceRepository) {
        this.checkpointRepository = checkpointRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional
    public void execute(RemoveFoodFromCheckpointRequest request,
            RemoveFoodFromCheckpointPresenter presenter) {
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());

        Checkpoint checkpoint = this.checkpointRepository.findByIdAndRaceId(request.checkpointId(),
                request.raceId());
        checkpoint.removeFood(request.command().getQuantity(), request.foodId());

        this.checkpointRepository.save(checkpoint);
        presenter.present(new RemoveFoodFromCheckpointResponse(true));
    }
}
