package devgraft.dgcinemabackend.movie.domain;

import java.util.List;

public interface MovieRepository {
	List<Movie> findAllByOrderByReleaseDateAsc();

	List<Movie> findAllByTitleContainingOrderByReleaseDateAsc(final String title);
}
