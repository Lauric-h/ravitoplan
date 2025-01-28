package com.java.ravito_plan.unit.domain.usecase.race.race;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.ravito_plan.TestConfig;
import com.java.ravito_plan.mock.domain.race.InMemoryRaceRepository;
import com.java.ravito_plan.race.domain.model.Race;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesImpl;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesPresenter;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesRequest;
import com.java.ravito_plan.race.domain.usecase.race.showAllRaces.ShowAllRacesResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class TestShowAllRaces implements ShowAllRacesPresenter {

	@Autowired
	private InMemoryRaceRepository raceRepository;

	private ShowAllRacesImpl showAllRaces;

	private ShowAllRacesResponse response;

	@Override
	public void present(ShowAllRacesResponse response) {
		this.response = response;
	}

	@BeforeEach
	public void setUp() {
		this.raceRepository.clear();
		this.showAllRaces = new ShowAllRacesImpl(this.raceRepository);
	}

	@Test
	void should_return_empty_list_when_no_races() {
		this.showAllRaces.execute(new ShowAllRacesRequest(1L), this);

		assertThat(this.response.races()).isEmpty();
	}

	@Test
	void should_return_all_races_for_user() {
		Long userId = 1L;
		Race race1 = createRace(1L, userId, "Race 1");
		Race race2 = createRace(2L, userId, "Race 2");
		Race otherUserRace = createRace(3L, 2L, "Other user race");

		this.raceRepository.save(race1);
		this.raceRepository.save(race2);
		this.raceRepository.save(otherUserRace);

		this.showAllRaces.execute(new ShowAllRacesRequest(userId), this);

		assertThat(this.response.races())
				.hasSize(2)
				.extracting(Race::getName)
				.containsExactlyInAnyOrder("Race 1", "Race 2");
	}

	@Test
	void should_return_races_ordered_by_date() {
		Long userId = 1L;
		LocalDate today = LocalDate.now();
		Race oldRace = createRace(1L, userId, "Old Race", today.minusDays(2));
		Race recentRace = createRace(2L, userId, "Recent Race", today);
		Race futureRace = createRace(3L, userId, "Future Race", today.plusDays(2));

		this.raceRepository.save(oldRace);
		this.raceRepository.save(recentRace);
		this.raceRepository.save(futureRace);

		this.showAllRaces.execute(new ShowAllRacesRequest(userId), this);

		assertThat(this.response.races())
				.hasSize(3)
				.extracting(Race::getName)
				.containsExactly("Old Race", "Recent Race", "Future Race");
	}

	private Race createRace(Long id, Long userId, String name) {
		return createRace(id, userId, name, LocalDate.now());
	}

	private Race createRace(Long id, Long userId, String name, LocalDate date) {
		Race race = new Race(
				name,
				date,
				10, // distance
				100, // elevation positive
				100, // elevation negative
				"Test City",
				"75000"
		);
		race.setId(id);
		race.setUserId(userId);
		return race;
	}
}
