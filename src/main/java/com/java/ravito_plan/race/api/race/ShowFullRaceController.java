package com.java.ravito_plan.race.api.race;

import com.java.ravito_plan.common.api.CurrentUser;
import com.java.ravito_plan.race.application.dto.internal.RaceUserDto;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRace;
import com.java.ravito_plan.race.domain.usecase.race.showFullRace.ShowFullRaceRequest;
import com.java.ravito_plan.race.infrastructure.presenter.race.showFullRace.ShowFullRaceJsonPresenter;
import com.java.ravito_plan.race.infrastructure.presenter.race.showFullRace.ShowFullRaceViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races/{id}/full")
public class ShowFullRaceController {

    private final ShowFullRace usecase;
    private final ShowFullRaceJsonPresenter presenter;

    public ShowFullRaceController(ShowFullRace usecase, ShowFullRaceJsonPresenter presenter) {
        this.usecase = usecase;
        this.presenter = presenter;
    }

    @GetMapping
    public ResponseEntity<ShowFullRaceViewModel> showFullRace(@PathVariable Long id,
            @CurrentUser RaceUserDto user) {
        this.usecase.execute(new ShowFullRaceRequest(id, user.id), this.presenter);
        return ResponseEntity.ok(this.presenter.getViewModel());
    }
}
