package com.java.ravito_plan.race.infrastructure.repository;

import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.ports.repository.CheckpointFoodRepository;
import org.springframework.stereotype.Component;

@Component
public class CheckpointFoodRepositoryJpaAdapter implements CheckpointFoodRepository {

    private final ImportedCheckpointFoodRepository repository;

    public CheckpointFoodRepositoryJpaAdapter(ImportedCheckpointFoodRepository repository) {
        this.repository = repository;
    }

    @Override
    public CheckpointFood findByFoodIdAndCheckpointId(Long foodId, Long checkpointId) {
        return this.repository.findByFoodIdAndCheckpointId(foodId, checkpointId).orElseThrow();
    }
}
