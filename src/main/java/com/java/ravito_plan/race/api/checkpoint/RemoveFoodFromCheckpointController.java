package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.command.DeleteFoodCommand;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointRequest;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.removeFoodFromCheckpoint.RemoveFoodFromCheckpointJsonPresenter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints/{checkpointId}/foods/{foodId}")
public class RemoveFoodFromCheckpointController {

    private final RemoveFoodFromCheckpoint usecase;
    private final RemoveFoodFromCheckpointJsonPresenter presenter;

    public RemoveFoodFromCheckpointController(RemoveFoodFromCheckpoint usecase,
            RemoveFoodFromCheckpointJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFoodFromCheckpoint(@PathVariable Long raceId,
            @PathVariable Long checkpointId, @PathVariable Long foodId,
            @CurrentUser RaceUserDto user, @Valid @RequestBody DeleteFoodCommand command) {
        this.usecase.execute(
                new RemoveFoodFromCheckpointRequest(raceId, checkpointId, user.id, foodId,
                        command.getQuantity()), this.presenter);

        return ResponseEntity.noContent().build();
    }
}
