package devgraft.dgcinemabackend.runningtime.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import devgraft.dgcinemabackend.movie.domain.Movie;

public interface RunningTimeRepository {
	List<RunningTime> findAllByMovie(final Movie movie);

	List<RunningTime> findAllByStartTimeGreaterThanEqualAndMovieIn(final LocalDateTime startTimeIsGreaterThan,
		List<Movie> movies);

	Optional<RunningTime> findById(final Long runningTimeId);
}
