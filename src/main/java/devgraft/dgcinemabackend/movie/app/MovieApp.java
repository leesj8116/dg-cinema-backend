package devgraft.dgcinemabackend.movie.app;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieRepository;

@Service
class MovieApp {
	private final MovieRepository movieRepository;

	public MovieApp(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public void register(final Movie movie) {
		movieRepository.save(new Movie());
	}
}
