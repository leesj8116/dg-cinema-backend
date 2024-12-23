package devgraft.dgcinemabackend.movie.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieRepository;

@Component
class MovieAdapter implements MovieRepository {
	private final MovieJpaRepository movieJpaRepository;

	public MovieAdapter(MovieJpaRepository movieJpaRepository) {
		this.movieJpaRepository = movieJpaRepository;
	}

	@Override
	public Movie save(final Movie movie) {
		return movieJpaRepository.save(movie);
	}

	@Override
	public Optional<Movie> find(final Long id) {
		return movieJpaRepository.findById(id);
	}

}
