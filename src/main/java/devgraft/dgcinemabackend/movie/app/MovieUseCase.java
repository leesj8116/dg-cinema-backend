package devgraft.dgcinemabackend.movie.app;

import java.util.List;

import devgraft.dgcinemabackend.movie.domain.MovieResult;

public interface MovieUseCase {
	List<MovieResult> getMovies();
}
