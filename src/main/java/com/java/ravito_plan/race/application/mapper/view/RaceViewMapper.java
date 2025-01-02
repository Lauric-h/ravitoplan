package com.java.ravito_plan.race.application.mapper.view;

import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Race;
import java.util.Map;

public class RaceViewMapper {

    public static RaceSummaryView toRaceSummaryView(Race race) {
        return new RaceSummaryView(race.getName(), race.getDate(), race.getDistance(),
                race.getElevationPositive(), race.getElevationNegative(), race.getCity(),
                race.getPostalCode());
    }

    public static RaceDetailView toRaceDetailView(Race race, Map<Long, RaceFoodDto> foods) {
        return new RaceDetailView(race.getName(), race.getDate(), race.getDistance(),
                race.getElevationPositive(), race.getElevationNegative(), race.getCity(),
                race.getPostalCode(), race.getCheckpoints().stream()
                .map(cp -> CheckpointViewMapper.toCheckpointDetailView(cp, foods)).toList(),
                race.getSegments().stream().map(SegmentViewMapper::toSegmentView).toList());
    }
}
