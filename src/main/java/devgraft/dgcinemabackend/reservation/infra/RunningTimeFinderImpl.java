package devgraft.dgcinemabackend.reservation.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;

@Component
class RunningTimeFinderImpl implements RunningTimeFinder {
	private final RunningTimeRepository runningTimeRepository;

	RunningTimeFinderImpl(RunningTimeRepository runningTimeRepository) {
		this.runningTimeRepository = runningTimeRepository;
	}

	@Override
	public Optional<RunningTime> findById(Long runningTimeId) {
		return runningTimeRepository.findById(runningTimeId);
	}
}
