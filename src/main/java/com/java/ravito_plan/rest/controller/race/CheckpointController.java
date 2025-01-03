package com.java.ravito_plan.rest.controller.race;

import static org.springframework.http.HttpStatus.OK;

import com.java.ravito_plan.race.application.dto.command.AddFoodCommand;
import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.command.DeleteFoodCommand;
import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.application.dto.view.RaceDetailView;
import com.java.ravito_plan.race.application.service.CheckpointService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints")
public class CheckpointController {

    private final CheckpointService checkpointService;

    public CheckpointController(CheckpointService checkpointService) {
        this.checkpointService = checkpointService;
    }

    @PostMapping("/{checkpointId}/foods")
    public ResponseEntity<CheckpointView> addFoodToCheckpoint(@PathVariable("raceId") Long raceId,
            @PathVariable("checkpointId") Long checkpointId,
            @Valid @RequestBody AddFoodCommand addFoodCommand) {
       CheckpointView checkpointView = this.checkpointService.addFoodToCheckpoint(addFoodCommand);

        return ResponseEntity.ok(checkpointView);
    }

    @DeleteMapping("/{checkpointId}/foods/{foodId}")
    public ResponseEntity<Void> removeFoodFromCheckpoint(@PathVariable("raceId") Long raceId,
            @PathVariable("checkpointId") Long checkpointId, @PathVariable("foodId") Long foodId,
            @Valid @RequestBody DeleteFoodCommand deleteFoodCommand) {
        this.checkpointService.removeFoodFromCheckpoint(deleteFoodCommand);
        return ResponseEntity.noContent().build();
    }
}
