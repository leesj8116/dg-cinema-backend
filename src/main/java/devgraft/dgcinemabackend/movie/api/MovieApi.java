package devgraft.dgcinemabackend.movie.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.movie.app.MovieApp;
import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieContext;

@RestController
public class MovieApi {
	private final MovieApp movieApp;

	public MovieApi(MovieApp movieApp) {
		this.movieApp = movieApp;
	}

	@GetMapping("/movie")
	public List<Movie> getMovies() {
		return movieApp.getMovies();
	}

	@PostMapping("/movie")
	public void createMovie(MovieContext context) {
		movieApp.register(context);
	}
}
