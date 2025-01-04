package com.java.ravito_plan.race.api.checkpoint;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpoint;
import com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint.DeleteCheckpointRequest;
import com.java.ravito_plan.race.infrastructure.presenter.checkpoint.deleteCheckpoint.DeleteCheckpointJsonPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{raceId}/checkpoints/{checkpointId}")
public class DeleteCheckpointController {

    private final DeleteCheckpoint usecase;
    private final DeleteCheckpointJsonPresenter presenter;
    private final UserPort userPort;

    public DeleteCheckpointController(DeleteCheckpoint usecase,
            DeleteCheckpointJsonPresenter presenter, UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCheckpoint(@PathVariable Long raceId,
            @PathVariable Long checkpointId, @AuthenticationPrincipal UserDetails userDetails) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new DeleteCheckpointRequest(raceId, checkpointId, user.id), this.presenter);
        return ResponseEntity.noContent().build();
    }
}
