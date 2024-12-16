package devgraft.dgcinemabackend.movie.app;

import java.util.List;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieContext;
import devgraft.dgcinemabackend.movie.domain.MovieRepository;

@Service
public class MovieApp {
	private final MovieRepository movieRepository;

	public MovieApp(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public void register(MovieContext context) {
		Movie movie = new Movie(context.title(), context.director(), context.releaseDate());

		movieRepository.save(movie);
	}

	public List<Movie> getMovies() {
		return movieRepository.findAllByOrderByReleaseDateAsc();
	}
}
