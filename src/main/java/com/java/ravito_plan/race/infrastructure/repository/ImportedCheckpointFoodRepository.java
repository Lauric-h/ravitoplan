package com.java.ravito_plan.race.infrastructure.repository;

import com.java.ravito_plan.race.domain.model.CheckpointFood;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedCheckpointFoodRepository extends JpaRepository<CheckpointFood, Long>  {

    Optional<CheckpointFood> findByFoodIdAndCheckpointId(Long foodId, Long checkpointId);
}
