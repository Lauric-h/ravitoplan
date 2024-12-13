package com.java.ravito_plan.rest.race;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.service.RaceApplicationService;
import com.java.ravito_plan.rest.view.race.CreateRaceRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    private final RaceApplicationService raceApplicationService;

    public RaceController(RaceApplicationService raceApplicationService) {
        this.raceApplicationService = raceApplicationService;
    }

    @GetMapping()
    public ResponseEntity<List<RaceDto>> getUserRaces(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<RaceDto> races = this.raceApplicationService.getAllUserRaces(
                userDetails.getUsername());

        return ResponseEntity.ok(races);
    }

    @PostMapping()
    public ResponseEntity<RaceDto> createUserRace(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateRaceRequest raceRequest) {
        RaceDto race = this.raceApplicationService.createRaceForUser(raceRequest.name,
                raceRequest.date, raceRequest.distance, raceRequest.elevationPositive,
                raceRequest.elevationNegative, raceRequest.city, raceRequest.postalCode,
                userDetails.getUsername());

        return ResponseEntity.ok(race);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceDto> getUserRace(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        RaceDto race = this.raceApplicationService.getUserRaceById(id, userDetails.getUsername());
        return ResponseEntity.ok(race);
    }
}
