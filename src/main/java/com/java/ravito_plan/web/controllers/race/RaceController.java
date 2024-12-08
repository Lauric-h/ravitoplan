package com.java.ravito_plan.web.controllers.race;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.service.RaceApplicationService;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RaceController {

    private final RaceApplicationService raceApplicationService;

    public RaceController(RaceApplicationService raceApplicationService) {
        this.raceApplicationService = raceApplicationService;
    }

    // findAllRacesByUserId
    @GetMapping("/races")
    public String getRaces(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List< RaceDto> races = this.raceApplicationService.getAllUserRaces(userDetails.getUsername());
        model.addAttribute("races", races);
        return "race/list";
    }

    // readOneRaceForUser

    // createOneRaceForUser

    // editRace

    // removeRace


}
