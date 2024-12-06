package devgraft.dgcinemabackend.cinema.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import devgraft.dgcinemabackend.cinema.domain.CinemaDto;

@SpringBootTest
class CinemaAppTest {
	private final CinemaApp cinemaApp;

	@Autowired
	public CinemaAppTest(CinemaApp cinemaApp) {
		this.cinemaApp = cinemaApp;
	}

	@Test
	@DisplayName("영화관을 등록한다")
	void createCinema() {
		// given
		CinemaDto cinemaDto = CinemaDto.builder()
			.name("DG신촌")
			.location("신촌촌촌")
			.build();
		// when
		CinemaDto result = cinemaApp.createCinema(cinemaDto);

		// then
		Assertions.assertThat(result.getCinemaId()).isNotNull();
	}

}