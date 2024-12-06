package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RaceApplicationService {

    private final RaceRepository raceRepository;
    private final UserPort userPort;

    public RaceApplicationService(RaceRepository raceRepository, UserPort userPort) {
        this.raceRepository = raceRepository;
        this.userPort = userPort;
    }

    public List<RaceDto> getRacesForUser(Long userId) {
        ExternalUserDto user = this.userPort.getUserById(userId);

        if (user == null ) {
            // TODO throw better exception
            throw new IllegalArgumentException("User not found");
        }

        List<Race> races = this.raceRepository.findAllByUserId(userId);

        return races.stream().map(RaceMapper::toRaceDto).toList();
    }
}
