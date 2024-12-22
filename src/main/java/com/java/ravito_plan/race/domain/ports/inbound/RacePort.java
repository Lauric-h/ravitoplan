package com.java.ravito_plan.race.domain.ports.inbound;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import java.util.List;

public interface RacePort {

    List<RaceSummaryView> getAllUserRaces();

    RaceSummaryView getUserRaceById(Long raceId);

    RaceDetailView getUserFullRaceById(Long raceId);

    RaceSummaryView createRaceForUser(CreateRaceCommand command);

    void updateRaceForUser(UpdateRaceCommand command);

    void deleteRaceForUser(Long raceId);
}
