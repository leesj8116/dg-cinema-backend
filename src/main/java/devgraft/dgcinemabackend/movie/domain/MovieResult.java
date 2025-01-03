package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;

public record MovieResult(
	String title,
	String director,
	LocalDate releaseDate
) {
	public static MovieResult from(Movie movie) {
		return new MovieResult(
			movie.getTitle(),
			movie.getDirector(),
			movie.getReleaseDate()
		);
	}
}
