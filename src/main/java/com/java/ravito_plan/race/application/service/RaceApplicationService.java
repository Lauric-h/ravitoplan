package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.application.mapper.view.RaceViewMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.FoodPort;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.ports.UserPort;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RaceApplicationService extends BaseApplicationService {

    public RaceApplicationService(RaceRepository raceRepository, UserPort userPort) {
        super(raceRepository, userPort);
    }

    @Transactional
    public void deleteRaceForUser(Long raceId) {
       this.verifyUserOwnsRace(raceId);

        this.raceRepository.deleteById(raceId);
    }
}
