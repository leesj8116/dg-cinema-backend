package devgraft.dgcinemabackend.movie.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.movie.app.MovieApp;
import devgraft.dgcinemabackend.movie.domain.MovieContext;

@RestController
public class MovieApi {
	private final MovieApp movieApp;

	public MovieApi(MovieApp movieApp) {
		this.movieApp = movieApp;
	}

	@PostMapping("/movie")
	public void createMovie(MovieContext context) {
		movieApp.register(context);
	}
}
