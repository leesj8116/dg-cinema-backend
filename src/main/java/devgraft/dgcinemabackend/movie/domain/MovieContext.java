package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;

public record MovieContext(Long movieId, String title, String director, LocalDate releaseDate) {
	public MovieContext {
		if (title == null) {
			throw new IllegalArgumentException(MovieExceptionMessage.TITLE_HAS_REQUIRED.getMessage());
		}
		if (title.length() > 200) {
			throw new IllegalArgumentException(MovieExceptionMessage.TITLE_HAS_LENGTH_UNDER_200.getMessage());
		}
		if (director == null) {
			throw new IllegalArgumentException(MovieExceptionMessage.DIRECTOR_HAS_REQUIRED.getMessage());
		}
		if (director.length() > 50) {
			throw new IllegalArgumentException(MovieExceptionMessage.DIRECTOR_HAS_LENGTH_UNDER_50.getMessage());
		}
	}
}
