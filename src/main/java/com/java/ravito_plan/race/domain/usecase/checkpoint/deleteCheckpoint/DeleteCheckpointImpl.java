package com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint;

import com.java.ravito_plan.race.domain.ports.repository.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteCheckpointImpl implements DeleteCheckpoint {

    private final CheckpointRepository checkpointRepository;
    private final RaceRepository raceRepository;

    public DeleteCheckpointImpl(CheckpointRepository checkpointRepository,
            RaceRepository raceRepository) {
        this.checkpointRepository = checkpointRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    @Transactional
    public void execute(DeleteCheckpointRequest request, DeleteCheckpointPresenter presenter) {
        // Verify user owns race
        this.raceRepository.existsByIdAndUserId(request.raceId(), request.userId());

        // Verify race owns CP
        this.checkpointRepository.findByIdAndRaceId(request.checkpointId(), request.raceId());

        this.checkpointRepository.deleteById(request.checkpointId());
        presenter.present(new DeleteCheckpointResponse(true));
    }
}
