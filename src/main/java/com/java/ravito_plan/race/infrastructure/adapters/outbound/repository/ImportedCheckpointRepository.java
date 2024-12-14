package com.java.ravito_plan.race.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.race.domain.model.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedCheckpointRepository extends JpaRepository<Checkpoint, Long> {

}
