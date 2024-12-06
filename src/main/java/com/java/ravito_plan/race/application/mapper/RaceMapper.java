package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.domain.model.Race;

public class RaceMapper {

    public static RaceDto toRaceDto(Race race) {
        return new RaceDto(race.getName(), race.getDistance(), race.getElevationPositive(),
                race.getElevationNegative(), race.getCity(), race.getPostalCode());
    }

    public static Race toRace(RaceDto raceDto) {
        return new Race(raceDto.name, raceDto.distance, raceDto.elevationPositive,
                raceDto.elevationNegative, raceDto.city, raceDto.postalCode);
    }
}
