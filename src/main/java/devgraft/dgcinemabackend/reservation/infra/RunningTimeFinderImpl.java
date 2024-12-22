package devgraft.dgcinemabackend.reservation.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RunningTimeFinderImpl implements RunningTimeFinder {
	private final RunningTimeRepository runningTimeRepository;

	@Override
	public Optional<RunningTime> findById(final Long runningTimeId) {
		return runningTimeRepository.findById(runningTimeId);
	}
}
