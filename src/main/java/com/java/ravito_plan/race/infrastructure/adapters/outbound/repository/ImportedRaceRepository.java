package com.java.ravito_plan.race.infrastructure.adapters.outbound.repository;

import com.java.ravito_plan.race.domain.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedRaceRepository extends JpaRepository<Race, Long> {

}
