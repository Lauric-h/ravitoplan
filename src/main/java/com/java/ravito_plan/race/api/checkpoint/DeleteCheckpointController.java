package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointRequest;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.deleteCheckpoint.DeleteCheckpointJsonPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints/{checkpointId}")
public class DeleteCheckpointController {

    private final DeleteCheckpoint usecase;
    private final DeleteCheckpointJsonPresenter presenter;

    public DeleteCheckpointController(DeleteCheckpoint usecase,
            DeleteCheckpointJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCheckpoint(@PathVariable Long raceId,
            @PathVariable Long checkpointId, @CurrentUser RaceUserDto user) {
        this.usecase.execute(new DeleteCheckpointRequest(raceId, checkpointId, user.id),
                this.presenter);
        return ResponseEntity.noContent().build();
    }
}
