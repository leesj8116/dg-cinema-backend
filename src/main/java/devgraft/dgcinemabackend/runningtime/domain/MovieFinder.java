package devgraft.dgcinemabackend.runningtime.domain;

import java.util.List;

import devgraft.dgcinemabackend.movie.domain.Movie;

public interface MovieFinder {
	List<Movie> findMoviesByMovieTitle(String title);
}
