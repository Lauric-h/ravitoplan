package com.java.ravito_plan.race.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.race.application.exception.CheckpointNotFoundException;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.ports.outbound.CheckpointRepository;
import org.springframework.stereotype.Component;

@Component
public class CheckpointRepositoryAdapter implements CheckpointRepository {

    ImportedCheckpointRepository checkpointRepository;

    public CheckpointRepositoryAdapter(ImportedCheckpointRepository checkpointRepository) {
        this.checkpointRepository = checkpointRepository;
    }

    @Override
    public Checkpoint findById(Long id) {
        return this.checkpointRepository.findById(id).orElseThrow(() -> new CheckpointNotFoundException(id));
    }

    @Override
    public Checkpoint save(Checkpoint checkpoint) {
       return this.checkpointRepository.save(checkpoint);
    }

    @Override
    public void deleteById(Long id) {
        this.checkpointRepository.deleteById(id);
    }

    @Override
    public Checkpoint findByIdAndRaceId(Long id, Long raceId) {
        return this.checkpointRepository.findByIdAndRaceId(id, raceId).orElseThrow(() -> new CheckpointNotFoundException(id));
    }
}
