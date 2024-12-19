package devgraft.dgcinemabackend.runningtime.domain;

import java.time.LocalDateTime;
import java.util.List;

import devgraft.dgcinemabackend.movie.domain.Movie;

public interface RunningTimeRepository {
	List<RunningTime> findAllByMovie(Movie movie);
	List<RunningTime> findAllByStartTimeGreaterThanEqualAndMovieIn(LocalDateTime startTimeIsGreaterThan, List<Movie> movies);
}
