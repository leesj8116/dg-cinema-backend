package devgraft.dgcinemabackend.runningtime.infra;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;

@Component
class RunningTimeAdapter implements RunningTimeRepository {
	private final RunningTimeJpaRepository runningTimeJpaRepository;

	RunningTimeAdapter(RunningTimeJpaRepository runningTimeJpaRepository) {
		this.runningTimeJpaRepository = runningTimeJpaRepository;
	}

	@Override
	public List<RunningTime> findAllByMovie(Movie movie) {
		return runningTimeJpaRepository.findAllByMovie(movie);
	}

	@Override
	public List<RunningTime> findAllByStartTimeGreaterThanEqualAndMovie(LocalDateTime startTimeAfter, Movie movie) {
		return runningTimeJpaRepository.findAllByStartTimeGreaterThanEqualAndMovie(startTimeAfter, movie);
	}
}
