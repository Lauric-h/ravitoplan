package com.java.ravito_plan.unit.domain.usecase.race.race;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.application.exception.RaceNotFoundException;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.usecase.race.showRace.ShowRaceImpl;
import com.java.ravito_plan.race.domain.usecase.race.showRace.ShowRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.showRace.ShowRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.showRace.ShowRaceResponse;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowRace implements ShowRacePresenter {

  @Autowired private InMemoryRaceRepository raceRepository;

  private ShowRaceImpl showRace;

  private ShowRaceResponse response;

  @BeforeEach
  public void setUp() {
    this.raceRepository.clear();
    this.showRace = new ShowRaceImpl(raceRepository);
  }

  @Override
  public void present(ShowRaceResponse response) {
    this.response = response;
  }

  @Test
  public void test_showRace_not_found_throws_exception() {
    Assertions.assertThatExceptionOfType(RaceNotFoundException.class)
        .isThrownBy(() -> this.showRace.execute(new ShowRaceRequest(1L, 1L), this));
  }

  @Test
  public void test_showRace() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    Long userId = 1L;
    race.setUserId(userId);
    race.setId(1L);
    this.raceRepository.addRace(race);

    this.showRace.execute(new ShowRaceRequest(1L, 1L), this);

    assertThat(this.response.race()).usingRecursiveComparison().isEqualTo(race);
  }
}
