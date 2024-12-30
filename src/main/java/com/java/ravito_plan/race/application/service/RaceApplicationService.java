package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class RaceApplicationService extends BaseApplicationService {

    private final FoodPort foodPort;

    public RaceApplicationService(RaceRepository raceRepository, UserPort userPort,
            FoodPort foodPort) {
        super(raceRepository, userPort);
        this.foodPort = foodPort;
    }

    @Transactional(readOnly = true)
    public List<RaceSummaryView> getAllUserRaces() {
        RaceUserDto user = this.getCurrentUser();

        List<Race> races = this.raceRepository.findAllByUserId(user.id);
        return races.stream().map(RaceViewMapper::toRaceSummaryView).toList();
    }

    @Transactional(readOnly = true)
    public RaceSummaryView getUserRaceById(Long raceId) {
        RaceUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceViewMapper.toRaceSummaryView(race);
    }

    @Transactional(readOnly = true)
    public RaceDetailView getUserFullRaceById(Long raceId) {
        RaceUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);

        Map<Long, FoodDto> foods = this.foodPort.getFoodsByIds(this.getAllFoodIdsForRace(race));

        return RaceViewMapper.toRaceDetailView(race, foods);
    }


    @Transactional
    public RaceSummaryView createRaceForUser(CreateRaceCommand command) {
        RaceUserDto user = this.getCurrentUser();

        Race race = RaceMapper.toRace(command);
        race.setUserId(user.id);

        Race createdRace = this.raceRepository.save(race);
        return RaceViewMapper.toRaceSummaryView(createdRace);
    }

    @Transactional
    public void updateRaceForUser(UpdateRaceCommand command) {
        RaceUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(command.getId(), user.id);

        race.updateFields(RaceMapper.toRace(command));

        race.validate();

        this.raceRepository.save(race);
    }

    @Transactional
    public void deleteRaceForUser(Long raceId) {
       this.verifyUserOwnsRace(raceId);

        this.raceRepository.deleteById(raceId);
    }
}
