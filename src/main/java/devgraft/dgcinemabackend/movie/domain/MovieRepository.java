package devgraft.dgcinemabackend.movie.domain;

import java.util.Optional;

public interface MovieRepository {
	Movie save(Movie movie);
	
	Optional<Movie> find(Long id);
}
