package devgraft.dgcinemabackend.runningtime.infra;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

interface RunningTimeJpaRepository extends JpaRepository<RunningTime, Long> {
	List<RunningTime> findAllByMovie(Movie movie);
	List<RunningTime> findAllByStartTimeGreaterThanEqualAndMovie(LocalDateTime startTimeAfter, Movie movie);
}
