package devgraft.dgcinemabackend.runningtime.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RunningTimeAdapter implements RunningTimeRepository {
	private final RunningTimeJpaRepository runningTimeJpaRepository;

	@Override
	public List<RunningTime> findAllByMovie(final Movie movie) {
		return runningTimeJpaRepository.findAllByMovie(movie);
	}

	@Override
	public List<RunningTime> findAllByStartTimeGreaterThanEqualAndMovieIn(final LocalDateTime startTimeIsGreaterThan,
		List<Movie> movies) {
		return runningTimeJpaRepository.findAllByStartTimeGreaterThanEqualAndMovieIn(startTimeIsGreaterThan, movies);
	}

	@Override
	public Optional<RunningTime> findById(final Long runningTimeId) {
		return runningTimeJpaRepository.findById(runningTimeId);
	}
}
