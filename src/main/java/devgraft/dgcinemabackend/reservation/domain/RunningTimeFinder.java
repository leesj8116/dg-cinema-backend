package devgraft.dgcinemabackend.reservation.domain;

import java.util.Optional;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

public interface RunningTimeFinder {
	Optional<RunningTime> findById(Long runningTimeId);
}
