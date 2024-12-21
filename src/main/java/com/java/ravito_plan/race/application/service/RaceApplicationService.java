package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RaceApplicationService extends BaseApplicationService {

    CheckpointRepository checkpointRepository;

    public RaceApplicationService(RaceRepository raceRepository, UserPort userPort,
            CheckpointRepository checkpointRepository) {
        super(raceRepository, userPort);
        this.checkpointRepository = checkpointRepository;
    }

    public List<RaceSummaryView> getAllUserRaces() {
        ExternalUserDto user = this.getCurrentUser();

        List<Race> races = this.raceRepository.findAllByUserId(user.id);
        return races.stream().map(RaceViewMapper::toRaceSummaryView).toList();
    }

    public RaceSummaryView getUserRaceById(Long raceId) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceViewMapper.toRaceSummaryView(race);
    }

    public RaceDetailView getUserFullRaceById(Long raceId) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceViewMapper.toRaceDetailView(race);
    }

    public RaceSummaryView createRaceForUser(CreateRaceCommand command) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = RaceMapper.toRace(command);
        race.setUserId(user.id);

        Race createdRace = this.raceRepository.save(race);
        return RaceViewMapper.toRaceSummaryView(createdRace);
    }

    public void updateRaceForUser(UpdateRaceCommand command) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(command.getId(), user.id);

        race.updateFields(race.getName(), race.getDate(), race.getDistance(), race.getElevationPositive(), race.getElevationNegative(), race.getCity(),
                race.getPostalCode());

        race.validate();

        this.raceRepository.save(race);
    }

    public void deleteRaceForUser(Long raceId) {
        ExternalUserDto user = this.getCurrentUser();

        this.raceRepository.deleteById(raceId);
    }
}
