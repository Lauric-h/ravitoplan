package com.java.ravito_plan.race.domain.ports.repository;

import com.java.ravito_plan.race.domain.model.Race;
import java.util.List;

public interface RaceRepository {

    Race save(Race race);
    List<Race> findAllByUserId(Long userId);
    Race findByIdAndUserId(Long id, Long userId);
    boolean existsByIdAndUserId(Long id, Long userId);
    void deleteById(Long id);
}
