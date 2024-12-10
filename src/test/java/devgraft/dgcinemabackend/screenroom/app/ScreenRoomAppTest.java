package devgraft.dgcinemabackend.screenroom.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import devgraft.dgcinemabackend.cinema.app.CinemaApp;
import devgraft.dgcinemabackend.cinema.domain.CinemaDto;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoomDto;

@SpringBootTest
class ScreenRoomAppTest {
	private final ScreenRoomApp screenRoomApp;
	private final CinemaApp cinemaApp;

	@Autowired
	public ScreenRoomAppTest(ScreenRoomApp screenRoomApp, CinemaApp cinemaApp) {
		this.screenRoomApp = screenRoomApp;
		this.cinemaApp = cinemaApp;
	}

	@Test
	@DisplayName("상영관을 등록한다")
	void createScreenRoom() {
		// given
		CinemaDto cinemaDto = cinemaApp.createCinema(CinemaDto.builder()
			.name("의정부")
			.location("경기도 의정부시")
			.build());

		ScreenRoomDto screenRoomDto = ScreenRoomDto.builder()
			.cinemaId(cinemaDto.getCinemaId())
			.screenNumber(1L)
			.build();

		// when
		ScreenRoomDto result = screenRoomApp.createScreenRoom(screenRoomDto);

		// then
		Assertions.assertThat(result.getCinemaId()).isNotNull();
		Assertions.assertThat(result.getScreenNumber()).isEqualTo(1);

	}
}