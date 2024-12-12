package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.mapper.RaceMapper;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private void verifyUserExists(String username) {
        if (!this.userPort.userExistsByUsername(username)) {
            throw new IllegalArgumentException("User Not Found");
        }
    }

    private ExternalUserDto getUserByUsername(String username) {
        ExternalUserDto user = this.userPort.getByUsername(username);
        if (null == user) {
            throw new IllegalArgumentException("User Not Found");
        }

        return user;
    }

    public List<RaceDto> getAllUserRaces(String username) {
        ExternalUserDto user = this.getUserByUsername(username);

        List<Race> races = this.raceRepository.findAllByUserId(user.id);
        return races.stream().map(RaceMapper::toRaceDto).toList();
    }

    public RaceDto getUserRaceById(Long raceId, String username) {
        ExternalUserDto user = this.getUserByUsername(username);

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        return RaceMapper.toRaceDto(race);
    }

    public RaceDto createRace(String name, LocalDate date, int distance, int elevationPositive,
            int elevationNegative, String city, String postalCode, String username) {
        ExternalUserDto user = this.getUserByUsername(username);

        Race race = RaceMapper.toRace(
                new RaceDto(name, date, distance, elevationPositive, elevationNegative, city,
                        postalCode));
        race.setUserId(user.id);

        Race createdRace = this.raceRepository.save(race);
        return RaceMapper.toRaceDto(createdRace);
    }

    public void updateRace(Long raceId, RaceDto raceDto, String username) {
        ExternalUserDto user = this.getUserByUsername(username);

        Race race = this.raceRepository.findByIdAndUserId(raceId, user.id);
        if (race == null) {
            throw new IllegalArgumentException("Race not found");
        }

        Race updatedRace = RaceMapper.toRace(raceDto);
        updatedRace.setId(raceId);

        this.raceRepository.save(updatedRace);
    }

    public void deleteRace(Long raceId, String username) {
        this.verifyUserExists(username);

        this.raceRepository.deleteById(raceId);
    }
}
