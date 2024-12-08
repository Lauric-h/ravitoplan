package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.domain.model.Race;
import java.util.List;

public interface RaceRepository {

    Race save(Race race);
    List<Race> findAllByUserId(Long userId);
    Race findById(Long id);
    void deleteById(Long id);
}
