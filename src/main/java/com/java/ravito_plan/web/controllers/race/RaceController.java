package com.java.ravito_plan.web.controllers.race;

import com.java.ravito_plan.race.application.service.RaceApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RaceController {

    private final RaceApplicationService raceApplicationService;

    public RaceController(RaceApplicationService raceApplicationService) {
        this.raceApplicationService = raceApplicationService;
    }


    // findAllRacesByUserId

    // readOneRaceForUser

    // createOneRaceForUser

    // editRace

    // removeRace



}
