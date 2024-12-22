package com.java.ravito_plan.rest.controller.race;

import static org.springframework.http.HttpStatus.OK;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.dto.view.RaceSummaryView;
import com.java.ravito_plan.race.application.service.RaceApplicationService;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RaceSummaryView>> getUserRaces() {
        List<RaceSummaryView> races = this.raceApplicationService.getAllUserRaces();
        return ResponseEntity.ok(races);
    }

    @PostMapping()
    public ResponseEntity<RaceSummaryView> createUserRace(@RequestBody CreateRaceCommand command) {
        RaceSummaryView race = this.raceApplicationService.createRaceForUser(command);
        return ResponseEntity.ok(race);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceSummaryView> getUserRace(@PathVariable Long id) {
        RaceSummaryView race = this.raceApplicationService.getUserRaceById(id);
        return ResponseEntity.ok(race);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editUserRace(@PathVariable Long id,
            @RequestBody UpdateRaceCommand command) {
        this.raceApplicationService.updateRaceForUser(command);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(id)
                .toUri();

        return ResponseEntity.status(OK).header(HttpHeaders.LOCATION, String.valueOf(location))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        this.raceApplicationService.deleteRaceForUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/full")
    public ResponseEntity<RaceDetailView> getFullRace(@PathVariable Long id) {
        RaceDetailView race = this.raceApplicationService.getUserFullRaceById(id);
        return ResponseEntity.ok(race);
    }
}
