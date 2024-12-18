package devgraft.dgcinemabackend.movie.domain;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
	Movie save(Movie movie);

	Optional<Movie> find(Long id);

	List<Movie> findAllByOrderByReleaseDateAsc();

	List<Movie> findAllByTitleContainingOrderByReleaseDateAsc(String title);
}
