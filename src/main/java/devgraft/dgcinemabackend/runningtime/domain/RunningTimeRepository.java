package devgraft.dgcinemabackend.runningtime.domain;

import java.util.List;

import devgraft.dgcinemabackend.movie.domain.Movie;

public interface RunningTimeRepository {
	List<RunningTime> findAllByMovie(Movie movie);
}
