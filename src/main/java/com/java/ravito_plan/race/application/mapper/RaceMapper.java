package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.domain.model.Race;

public class RaceMapper {

    public static RaceDto toRaceDto(Race race) {
        return new RaceDto(race.getName(), race.getDate(), race.getDistance(), race.getElevationPositive(),
                race.getElevationNegative(), race.getCity(), race.getPostalCode(), race.getCheckpoints().stream().map(
                CheckpointMapper::toCheckpointDto).toList());
    }

    public static Race toRace(RaceDto raceDto) {
        return new Race(raceDto.name, raceDto.date, raceDto.distance, raceDto.elevationPositive,
                raceDto.elevationNegative, raceDto.city, raceDto.postalCode);
    }
}
