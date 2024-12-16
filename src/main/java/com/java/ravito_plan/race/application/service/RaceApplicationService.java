package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.CheckpointDto;
import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.dto.RaceFullDto;
import com.java.ravito_plan.race.application.mapper.CheckpointMapper;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.CheckpointRepository;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class RaceApplicationService extends BaseApplicationService {

    CheckpointRepository checkpointRepository;

    public RaceApplicationService(RaceRepository raceRepository, UserPort userPort,
            CheckpointRepository checkpointRepository) {
        super(raceRepository, userPort);
        this.checkpointRepository = checkpointRepository;
    }

    public List<RaceDto> getAllUserRaces() {
        ExternalUserDto user = this.getCurrentUser();

        List<Race> races = this.raceRepository.findAllByUserId(user.id);
        return races.stream().map(RaceMapper::toRaceDto).toList();
    }

    public RaceDto getUserRaceById(Long raceId) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceMapper.toRaceDto(race);
    }

    public RaceFullDto getUserFullRaceById(Long raceId) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceMapper.toRaceFullDto(race);
    }

    public RaceDto createRaceForUser(String name, LocalDate date, int distance,
            int elevationPositive, int elevationNegative, String city, String postalCode) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = RaceMapper.toRace(
                new RaceDto(name, date, distance, elevationPositive, elevationNegative, city,
                        postalCode));
        race.setUserId(user.id);

        Race createdRace = this.raceRepository.save(race);
        return RaceMapper.toRaceDto(createdRace);
    }

    public void updateRaceForUser(Long raceId, String name, LocalDate date, int distance,
            int elevationPositive, int elevationNegative, String city, String postalCode) {
        ExternalUserDto user = this.getCurrentUser();

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);

        race.updateFields(name, date, distance, elevationPositive, elevationNegative, city,
                postalCode);

        race.validate();

        this.raceRepository.save(race);
    }

    public void deleteRaceForUser(Long raceId) {
        ExternalUserDto user = this.getCurrentUser();

        this.raceRepository.deleteById(raceId);
    }
}
