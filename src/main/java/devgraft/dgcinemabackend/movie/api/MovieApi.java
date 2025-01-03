package devgraft.dgcinemabackend.movie.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.movie.app.MovieUseCase;
import devgraft.dgcinemabackend.movie.domain.MovieResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class MovieApi {
	private final MovieUseCase movieUseCase;

	@GetMapping("/movie")
	public List<MovieResult> getMovies() {
		return movieUseCase.getMovies();
	}
}
