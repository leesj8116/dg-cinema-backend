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
		movieRepository.save(Movie.builder()
			.title(context.title())
			.director(context.director())
			.releaseDate(context.releaseDate())
			.build());
	}

	public List<Movie> getMovies() {
		return movieRepository.findAllByOrderByReleaseDateAsc();
	}
}
