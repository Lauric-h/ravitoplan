package com.java.ravito_plan.web.controllers.race;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.service.RaceApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RaceController {

    private final RaceApplicationService raceApplicationService;

    public RaceController(RaceApplicationService raceApplicationService) {
        this.raceApplicationService = raceApplicationService;
    }

    @GetMapping("/races")
    public String getRaces(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List< RaceDto> races = this.raceApplicationService.getAllUserRaces(userDetails.getUsername());
        model.addAttribute("races", races);
        return "race/list";
    }

    @GetMapping("/races/{id}")
    public String getRace(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, Model model) {
        RaceDto race = this.raceApplicationService.getUserRaceById(id, userDetails.getUsername());
        model.addAttribute("race", race);
        return "race/race";
    }

    // createOneRaceForUser
    @PostMapping("/races")
    public String createRace(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("race") @Valid RaceDto raceDto) {
        RaceDto race = this.raceApplicationService.createRace(raceDto, userDetails.getUsername());
        return "redirect:/races";
    }

    // editRace

    // removeRace


}
