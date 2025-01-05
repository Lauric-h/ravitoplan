package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRace;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRaceRequest;
import com.java.ravito_plan.race.infrastructure.presenter.race.deleteRace.DeleteRaceJsonPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{id}")
public class DeleteRaceController {

    private final DeleteRace usecase;
    private final DeleteRaceJsonPresenter presenter;

    public DeleteRaceController(DeleteRace usecase, DeleteRaceJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRace(@PathVariable Long id, @CurrentUser RaceUserDto user) {
        this.usecase.execute(new DeleteRaceRequest(id, user.id), this.presenter);

        return ResponseEntity.noContent().build();
    }
}
