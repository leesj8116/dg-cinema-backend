package devgraft.dgcinemabackend.movie.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class MovieAdapter implements MovieRepository {
	private final MovieJpaRepository movieJpaRepository;

	@Override
	public List<Movie> findAllByOrderByReleaseDateAsc() {
		return movieJpaRepository.findAllByOrderByReleaseDateAsc();
	}

	@Override
	public List<Movie> findAllByTitleContainingOrderByReleaseDateAsc(final String title) {
		return movieJpaRepository.findAllByTitleContainingOrderByReleaseDateAsc(title);
	}
}
