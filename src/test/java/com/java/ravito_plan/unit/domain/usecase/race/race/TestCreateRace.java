package com.java.ravito_plan.unit.domain.usecase.race.race;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.dto.RaceParams;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.ports.RaceFactory;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRaceImpl;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRacePresenter;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRaceRequest;
import com.java.ravito_plan.race.domain.usecase.race.createRace.CreateRaceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestCreateRace implements CreateRacePresenter {

	@Autowired
	private InMemoryRaceRepository repository;

	private CreateRaceImpl createRace;

	private CreateRaceResponse response;

	@MockBean
	RaceFactory raceFactory;

	@Override
	public void present(CreateRaceResponse response) {
		this.response = response;
	}

	@BeforeEach
	public void setUp() {
		this.repository.clear();
		this.createRace = new CreateRaceImpl(this.repository, this.raceFactory);
	}

	@Test
	public void test_createRace() {
		Race expectedRace = new Race("Test race", LocalDate.now(), 10, 100, 100, "Test city", "75011");
		RaceParams raceParams = new RaceParams("Test race", LocalDate.now(), 10, 100, 100, "Test city", "75011");

		when(this.raceFactory.create(raceParams)).thenReturn(expectedRace);

		this.createRace.execute(new CreateRaceRequest(1L, raceParams), this);

		assertThat(this.response.race().getUserId()).isEqualTo(1L);
		assertThat(this.response.race().getName()).	isEqualTo("Test race");
		assertThat(this.response.race().getPostalCode()).isEqualTo("75011");
		assertThat(this.response.race().getCity()).isEqualTo("Test city");
		assertThat(this.response.race().getDistance()).isEqualTo(10);
		assertThat(this.response.race().getElevationPositive()).isEqualTo(100);
		assertThat(this.response.race().getElevationNegative()).isEqualTo(100);
		assertThat(this.response.race().getStartCheckpoint()).isNotNull();
		assertThat(this.response.race().getFinishCheckpoint()).isNotNull();
		assertThat(this.response.race().getCheckpoints().size()).isEqualTo(2);
	}
}
