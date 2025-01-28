package com.java.ravito_plan.unit.domain.usecase.race.race;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.dto.RaceParams;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.RaceFactory;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRaceImpl;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.updateRace.UpdateRaceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestUpdateRace implements UpdateRacePresenter {

  @Autowired private InMemoryRaceRepository repository;

  private UpdateRaceImpl updateRace;

  private UpdateRaceResponse response;

  @MockBean private RaceFactory factory;

  @Override
  public void present(UpdateRaceResponse response) {
    this.response = response;
  }

  @BeforeEach
  public void setUp() {
    this.repository.clear();
    this.updateRace = new UpdateRaceImpl(this.repository, this.factory);
  }

  @Test
  public void test_updateRace() {
    Race race =
        new Race(
            "Before update", LocalDate.parse("2025-01-01"), 10, 1000, 1500, "Faverges", "74210");
    Long id = 1L;
    race.setUserId(id);
    race.setId(id);
    this.repository.addRace(race);

    RaceParams params =
        new RaceParams(
            "After update", LocalDate.parse("2025-10-01"), 100, 10000, 10000, "Chamonix", "74200");
    Race updatedRace =
        new Race(
            "After update", LocalDate.parse("2025-10-01"), 100, 10000, 10000, "Chamonix", "74200");

    Race expectedRace =
        new Race(
            "After update", LocalDate.parse("2025-10-01"), 100, 10000, 10000, "Chamonix", "74200");
    expectedRace.setUserId(id);
    expectedRace.setId(id);

    when(this.factory.create(params)).thenReturn(updatedRace);

    this.updateRace.execute(new UpdateRaceRequest(id, id, params), this);

    System.out.println(expectedRace.getId());
    System.out.println(race.getId());

    assertThat(this.response.race()).usingRecursiveComparison().isEqualTo(expectedRace);
  }
}
