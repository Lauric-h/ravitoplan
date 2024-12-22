package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.internal.UserDto;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.FoodPort;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RaceApplicationService extends BaseApplicationService {

    FoodPort foodPort;

    public RaceApplicationService(RaceRepository raceRepository, UserPort userPort,
            FoodPort foodPort) {
        super(raceRepository, userPort);
        this.foodPort = foodPort;
    }

    public List<RaceSummaryView> getAllUserRaces() {
        UserDto user = this.getCurrentUser();

        List<Race> races = this.raceRepository.findAllByUserId(user.id);
        return races.stream().map(RaceViewMapper::toRaceSummaryView).toList();
    }

    public RaceSummaryView getUserRaceById(Long raceId) {
        UserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceViewMapper.toRaceSummaryView(race);
    }

    public RaceDetailView getUserFullRaceById(Long raceId) {
        UserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);

        Map<Long, FoodDto> foods = this.foodPort.getFoodsByIds(
                this.getAllFoodIdsForRace(race));

        return RaceViewMapper.toRaceDetailView(race, foods);
    }


    public RaceSummaryView createRaceForUser(CreateRaceCommand command) {
        UserDto user = this.getCurrentUser();

        Race race = RaceMapper.toRace(command);
        race.setUserId(user.id);

        Race createdRace = this.raceRepository.save(race);
        return RaceViewMapper.toRaceSummaryView(createdRace);
    }

    public void updateRaceForUser(UpdateRaceCommand command) {
        UserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(command.getId(), user.id);

        race.updateFields(RaceMapper.toRace(command));

        race.validate();

        this.raceRepository.save(race);
    }

    public void deleteRaceForUser(Long raceId) {
        UserDto user = this.getCurrentUser();

        this.raceRepository.deleteById(raceId);
    }
}
