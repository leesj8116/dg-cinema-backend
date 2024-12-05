package devgraft.dgcinemabackend.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.movie.app.MovieApp;

@RestController
public class MovieApi {
	private final MovieApp movieApp;

	@Autowired
	public MovieApi(MovieApp movieApp) {
		this.movieApp = movieApp;
	}
}
