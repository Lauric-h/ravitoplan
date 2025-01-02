package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.UserPort;
import com.java.ravito_plan.race.domain.usecase.deleteRace.DeleteRaceInterface;
import com.java.ravito_plan.race.domain.usecase.deleteRace.DeleteRaceRequest;
import com.java.ravito_plan.race.infrastructure.presenter.deleteRace.DeleteRaceJsonPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{id}")
public class DeleteRaceController {

    private final DeleteRaceInterface usecase;
    private final DeleteRaceJsonPresenter presenter;
    private final UserPort userPort;

    public DeleteRaceController(DeleteRaceInterface usecase, DeleteRaceJsonPresenter presenter,
            UserPort userPort) {
        this.usecase = usecase;
        this.presenter = presenter;
        this.userPort = userPort;
    }

    @DeleteMapping
   public ResponseEntity<Void> deleteRace(@PathVariable Long id, @AuthenticationPrincipal
           UserDetails userDetails) {
        RaceUserDto user = this.userPort.getByUsername(userDetails.getUsername());
        this.usecase.execute(new DeleteRaceRequest(id, user.id), this.presenter);

        return ResponseEntity.noContent().build();
    }
}
