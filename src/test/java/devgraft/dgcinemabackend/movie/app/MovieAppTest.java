package devgraft.dgcinemabackend.movie.app;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import devgraft.dgcinemabackend.movie.domain.MovieDto;

@SpringBootTest
class MovieAppTest {
	private final MovieApp movieApp;

	@Autowired
	public MovieAppTest(MovieApp movieApp) {
		this.movieApp = movieApp;
	}

	@Test
	void createMovie() {
		// given
		MovieDto dto = MovieDto.builder()
			.title("홍길동전")
			.director("홍길동")
			.releaseDate(LocalDate.now())
			.build();

		// when
		movieApp.createMovie(dto);

		// then
	}
}