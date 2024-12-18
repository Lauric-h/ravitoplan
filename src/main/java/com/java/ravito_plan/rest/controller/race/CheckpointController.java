package com.java.ravito_plan.rest.controller.race;

import com.java.ravito_plan.race.application.dto.CheckpointDto;
import com.java.ravito_plan.race.application.dto.CheckpointFoodDto;
import com.java.ravito_plan.race.application.service.CheckpointService;
import com.java.ravito_plan.rest.view.race.CheckpointFoodRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints")
public class CheckpointController {

    private final CheckpointService checkpointService;

    public CheckpointController(CheckpointService checkpointService) {
        this.checkpointService = checkpointService;
    }

    @PostMapping("/{checkpointId}/foods")
    public ResponseEntity<CheckpointDto> addFoodToCheckpoint(@PathVariable("raceId") Long raceId,
            @PathVariable("checkpointId") Long checkpointId,
            @RequestBody CheckpointFoodRequest checkpointFoodRequest) {
        CheckpointDto checkpointDto = this.checkpointService.addFoodToCheckpoint(raceId,
                new CheckpointFoodDto(checkpointFoodRequest.foodId, checkpointId,
                        checkpointFoodRequest.quantity));

        return ResponseEntity.ok(checkpointDto);
    }
}
