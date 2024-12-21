package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.dto.RaceFullDto;
import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.RaceCommand;
import com.java.ravito_plan.race.domain.model.Race;

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

    public static Race toRace(RaceCommand command) {
        return Race.create(command.getName(), command.getDate(), command.getDistance(), command.getElevationPositive(),
                command.getElevationNegative(), command.getCity(), command.getPostalCode());
    }
}
