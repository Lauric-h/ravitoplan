package com.java.ravito_plan.race.infrastructure.repository;

import com.java.ravito_plan.race.domain.model.Checkpoint;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedCheckpointRepository extends JpaRepository<Checkpoint, Long> {
    Optional<Checkpoint> findByIdAndRaceId(Long id, Long raceId);
}
