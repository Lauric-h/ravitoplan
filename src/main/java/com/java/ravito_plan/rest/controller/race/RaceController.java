package com.java.ravito_plan.rest.controller.race;

import static org.springframework.http.HttpStatus.OK;

import com.java.ravito_plan.race.application.dto.CheckpointDto;
import com.java.ravito_plan.race.application.dto.RaceDto;
import com.java.ravito_plan.race.application.dto.RaceFullDto;
import com.java.ravito_plan.race.application.service.RaceApplicationService;
import com.java.ravito_plan.race.domain.model.CheckpointType;
import com.java.ravito_plan.rest.view.race.CheckpointRequest;
import com.java.ravito_plan.rest.view.race.RaceRequest;
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
    public ResponseEntity<List<RaceDto>> getUserRaces() {
        List<RaceDto> races = this.raceApplicationService.getAllUserRaces();
        return ResponseEntity.ok(races);
    }

    @PostMapping()
    public ResponseEntity<RaceDto> createUserRace(@RequestBody RaceRequest raceRequest) {
        RaceDto race = this.raceApplicationService.createRaceForUser(raceRequest.name,
                raceRequest.date, raceRequest.distance, raceRequest.elevationPositive,
                raceRequest.elevationNegative, raceRequest.city, raceRequest.postalCode);
        return ResponseEntity.ok(race);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceDto> getUserRace(@PathVariable Long id) {
        RaceDto race = this.raceApplicationService.getUserRaceById(id);
        return ResponseEntity.ok(race);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editUserRace(@PathVariable Long id,
            @RequestBody RaceRequest raceRequest) {
        this.raceApplicationService.updateRaceForUser(id, raceRequest.name, raceRequest.date,
                raceRequest.distance, raceRequest.elevationPositive, raceRequest.elevationNegative,
                raceRequest.city, raceRequest.postalCode);

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

    @PostMapping("/{id}/checkpoints")
    public ResponseEntity<Void> addCheckpoint(@PathVariable Long id,
            @RequestBody CheckpointRequest checkpointRequest) {
        RaceFullDto updatedRace = this.raceApplicationService.addCheckpoint(id,
                new CheckpointDto(checkpointRequest.name, checkpointRequest.distanceFromStart,
                        checkpointRequest.location, CheckpointType.valueOf(checkpointRequest.type),
                        checkpointRequest.estimatedTimeInMinuteFromStart,
                        checkpointRequest.cumulatedElevationGainFromStart,
                        checkpointRequest.cumulatedElevationLossFromStart,
                        checkpointRequest.carbsTarget));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/races/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.status(OK).header(HttpHeaders.LOCATION, String.valueOf(location))
                .build();
    }

    @PutMapping("/{id}/checkpoints/{checkpointId}")
    public ResponseEntity<Void> editCheckpoint(@PathVariable Long id,
            @PathVariable Long checkpointId, @RequestBody CheckpointRequest checkpointRequest) {
        this.raceApplicationService.updateCheckpoint(id, checkpointId,
                new CheckpointDto(checkpointRequest.name, checkpointRequest.distanceFromStart,
                        checkpointRequest.location, CheckpointType.valueOf(checkpointRequest.type),
                        checkpointRequest.estimatedTimeInMinuteFromStart,
                        checkpointRequest.cumulatedElevationGainFromStart,
                        checkpointRequest.cumulatedElevationLossFromStart,
                        checkpointRequest.carbsTarget));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/races/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.status(OK).header(HttpHeaders.LOCATION, String.valueOf(location))
                .build();
    }

    @DeleteMapping("/{id}/checkpoints/{checkpointId}")
    public ResponseEntity<Void> deleteCheckpoint(@PathVariable Long id,
            @PathVariable Long checkpointId) {
        this.raceApplicationService.deleteCheckpoint(id, checkpointId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<RaceFullDto> getFullRace(@PathVariable Long id) {
        RaceFullDto race = this.raceApplicationService.getUserFullRaceById(id);
        return ResponseEntity.ok(race);
    }

}
