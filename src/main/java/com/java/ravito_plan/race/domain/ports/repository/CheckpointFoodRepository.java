package com.java.ravito_plan.race.domain.ports.repository;

import com.java.ravito_plan.race.domain.model.CheckpointFood;

public interface CheckpointFoodRepository {

    CheckpointFood findByFoodIdAndCheckpointId(Long foodId, Long checkpointId);
}
