package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;

public record MovieContext(Long movieId, String title, String director, LocalDate releaseDate) {
	public MovieContext {
		if (title.length() > 200) {
			throw new IllegalArgumentException("Title length exceeds 200 characters");
		}
		if (director.length() > 50) {
			throw new IllegalArgumentException("Director length exceeds 50 characters");
		}
	}
}
