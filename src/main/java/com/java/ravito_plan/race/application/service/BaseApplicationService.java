package com.java.ravito_plan.race.application.service;

import com.java.ravito_plan.race.application.dto.ExternalUserDto;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.outbound.RaceRepository;
import com.java.ravito_plan.race.domain.ports.outbound.UserPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseApplicationService {

    protected final RaceRepository raceRepository;
    protected final UserPort userPort;

    public BaseApplicationService(RaceRepository raceRepository, UserPort userPort) {
        this.raceRepository = raceRepository;
        this.userPort = userPort;
    }

    protected ExternalUserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userPort.getByUsername(username);
    }

    protected void verifyUserOwnsRace(Long raceId) {
        ExternalUserDto currentUser = getCurrentUser();
        Race race = raceRepository.findByIdAndUserId(raceId, currentUser.id);
        if (race == null) {
            throw new IllegalArgumentException("Race not found for current user");
        }
    }
}
