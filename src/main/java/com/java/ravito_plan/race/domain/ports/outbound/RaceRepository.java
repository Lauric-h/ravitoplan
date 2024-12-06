package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.domain.model.Race;

public interface RaceRepository {

    Race save(Race race);
}
