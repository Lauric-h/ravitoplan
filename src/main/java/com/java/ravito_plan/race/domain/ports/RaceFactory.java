package com.java.ravito_plan.race.domain.ports;

import com.java.ravito_plan.race.domain.dto.RaceParams;
import com.java.ravito_plan.race.domain.model.Race;

public interface RaceFactory {

    Race create(RaceParams raceParams);
}
