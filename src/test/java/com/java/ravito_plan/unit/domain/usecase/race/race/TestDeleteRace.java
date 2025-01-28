package com.java.ravito_plan.unit.domain.usecase.race.race;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRaceImpl;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.deleteRace.DeleteRaceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestDeleteRace implements DeleteRacePresenter {

  @Autowired
  private InMemoryRaceRepository repository;

  private DeleteRaceImpl deleteRace;

  private DeleteRaceResponse response;

  @Override
  public void present(DeleteRaceResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.repository.clear();
    this.deleteRace = new DeleteRaceImpl(repository);
  }

  @Test
  public void test_deleteRace() {
    Race race = new Race("Test race", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    Long userId = 1L;
    race.setUserId(userId);
    race.setId(1L);
    this.repository.addRace(race);

    this.deleteRace.execute(new DeleteRaceRequest(1L, 1L), this);

    assertThat(this.repository.races).isEmpty();
    assertThat(this.response.success()).isTrue();
  }
}
