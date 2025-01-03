package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;

public record MovieContext(Long movieId, String title, String director, LocalDate releaseDate) {
	public MovieContext {
		if (title == null) {
			throw new MovieException(MovieErrorCode.TITLE_HAS_REQUIRED);
		}
		if (title.length() > 200) {
			throw new MovieException(MovieErrorCode.TITLE_HAS_LENGTH_UNDER_200);
		}
		if (director == null) {
			throw new MovieException(MovieErrorCode.DIRECTOR_HAS_REQUIRED);
		}
		if (director.length() > 50) {
			throw new MovieException(MovieErrorCode.DIRECTOR_HAS_LENGTH_UNDER_50);
		}
	}
}
