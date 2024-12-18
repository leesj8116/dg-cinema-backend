package devgraft.dgcinemabackend.runningtime.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieRepository;
import devgraft.dgcinemabackend.runningtime.domain.MovieFinder;

@Component
class MovieFinderImpl implements MovieFinder {
	private final MovieRepository movieRepository;

	public MovieFinderImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public List<Movie> findMoviesByMovieTitle(String title) {
		return movieRepository.findAllByTitleContainingOrderByReleaseDateAsc(title);
	}
}
