package devgraft.dgcinemabackend.runningtime.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

interface RunningTimeJpaRepository extends JpaRepository<RunningTime, Long> {
	List<RunningTime> findAllByMovie(final Movie movie);

	List<RunningTime> findAllByStartTimeGreaterThanEqualAndMovieIn(final LocalDateTime startTimeIsGreaterThan,
		final List<Movie> movies);

	Optional<RunningTime> findById(final Long runningTimeId);
}
