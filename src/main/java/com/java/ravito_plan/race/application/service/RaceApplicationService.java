package com.java.ravito_plan.race.application.service;

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

    private void verifyUserExists(Long userId) {
        if (!this.userPort.userExistsById(userId)) {
            // TODO throw better exception
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<RaceDto> getRacesForUser(Long userId) {
        this.verifyUserExists(userId);

        List<Race> races = this.raceRepository.findAllByUserId(userId);
        return races.stream().map(RaceMapper::toRaceDto).toList();
    }

    public RaceDto getRaceById(Long raceId, Long userId) {
        this.verifyUserExists(userId);

        Race race = this.raceRepository.findById(raceId);
        return RaceMapper.toRaceDto(race);
    }

    public void createRace(RaceDto raceDto, Long userId) {
        this.verifyUserExists(userId);

        this.raceRepository.save(RaceMapper.toRace(raceDto));
    }

    public void updateRace(Long raceId, RaceDto raceDto, Long userId) {
        this.verifyUserExists(userId);

        Race race = this.raceRepository.findById(raceId);
        if (race == null) {
            throw new IllegalArgumentException("Race not found");
        }

        Race updatedRace = RaceMapper.toRace(raceDto);
        updatedRace.setId(raceId);

        this.raceRepository.save(updatedRace);
    }

    public void deleteRace(Long raceId, Long userId) {
        this.verifyUserExists(userId);

        this.raceRepository.deleteById(raceId) ;
    }
}
