package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.repository.RaceRepository;
import com.java.ravito_plan.race.domain.ports.UserPort;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseApplicationService {

    protected final RaceRepository raceRepository;
    protected final UserPort userPort;

    public BaseApplicationService(RaceRepository raceRepository, UserPort userPort) {
        this.raceRepository = raceRepository;
        this.userPort = userPort;
    }

    protected RaceUserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userPort.getByUsername(username);
    }

    protected void verifyUserOwnsRace(Long raceId) {
        RaceUserDto currentUser = getCurrentUser();
        Race race = raceRepository.findByIdAndUserId(raceId, currentUser.id);
    }

    protected Set<Long> getAllFoodIdsForRace(Race race) {
        return race.getCheckpoints().stream().flatMap(cp -> cp.getCheckpointFoods().stream())
                .map(CheckpointFood::getFoodId).collect(Collectors.toSet());
    }
}
