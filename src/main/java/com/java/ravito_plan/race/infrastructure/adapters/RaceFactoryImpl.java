package com.java.ravito_plan.race.infrastructure.adapters;

import com.java.ravito_plan.race.domain.dto.RaceParams;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.RaceFactory;
import org.springframework.stereotype.Component;

@Component
public class RaceFactoryImpl implements RaceFactory {

    @Override
    public Race create(RaceParams raceParams) {
        return new Race(raceParams.name(), raceParams.date(), raceParams.distance(),
                raceParams.elevationPositive(), raceParams.elevationNegative(), raceParams.city(),
                raceParams.postalCode());
    }
}
