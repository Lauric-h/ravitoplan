package com.java.ravito_plan.race.infrastructure.repository;

import com.java.ravito_plan.race.domain.model.Race;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedRaceRepository extends JpaRepository<Race, Long> {
    List<Race> findAllByUserId(Long userId);
    Optional<Race> findByIdAndUserId(Long id, Long userId);
    boolean existsByIdAndUserId(Long userId, Long raceId);
}
