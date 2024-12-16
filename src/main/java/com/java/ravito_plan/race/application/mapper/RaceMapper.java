package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.dto.RaceFullDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.model.Segment;
import java.util.List;

public class RaceMapper {

    public static RaceFullDto toRaceFullDto(Race race) {
        return new RaceFullDto(race.getName(), race.getDate(), race.getDistance(),
                race.getElevationPositive(), race.getElevationNegative(), race.getCity(),
                race.getPostalCode(),
                race.getCheckpoints().stream().map(CheckpointMapper::toCheckpointDto).toList(),
                race.getSegments().stream().map(SegmentMapper::toSegmentDto).toList());
    }

    public static RaceDto toRaceDto(Race race) {
        return new RaceDto(race.getName(), race.getDate(), race.getDistance(),
                race.getElevationPositive(), race.getElevationNegative(), race.getCity(),
                race.getPostalCode());
    }

    public static Race toRace(RaceDto raceDto) {
        return Race.create(raceDto.name, raceDto.date, raceDto.distance, raceDto.elevationPositive,
                raceDto.elevationNegative, raceDto.city, raceDto.postalCode);
    }
}
