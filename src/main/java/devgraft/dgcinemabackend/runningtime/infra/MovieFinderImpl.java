package devgraft.dgcinemabackend.runningtime.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieRepository;
import devgraft.dgcinemabackend.runningtime.domain.MovieFinder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class MovieFinderImpl implements MovieFinder {
	private final MovieRepository movieRepository;

	public List<Movie> findMoviesByMovieTitle(final String title) {
		return movieRepository.findAllByTitleContainingOrderByReleaseDateAsc(title);
	}
}
