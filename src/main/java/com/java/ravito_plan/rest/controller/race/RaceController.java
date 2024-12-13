package com.java.ravito_plan.rest.controller.race;

import static org.springframework.http.HttpStatus.OK;

import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.service.RaceApplicationService;
import com.java.ravito_plan.rest.view.race.RaceRequest;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
            @RequestBody RaceRequest raceRequest) {
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> editUserRace(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id, @RequestBody RaceRequest raceRequest) {
        this.raceApplicationService.updateRaceForUser(id, raceRequest.name,
                raceRequest.date, raceRequest.distance, raceRequest.elevationPositive,
                raceRequest.elevationNegative, raceRequest.city, raceRequest.postalCode,
                userDetails.getUsername());

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("")
                        .buildAndExpand(id)
                        .toUri();

        return ResponseEntity.status(OK).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        this.raceApplicationService.deleteRaceForUser(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
