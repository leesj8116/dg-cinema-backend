package devgraft.dgcinemabackend.runningtime.app;

import java.util.List;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

public interface RunningTimeUseCase {
	List<RunningTime> getRunningTimesByMovieTitle(final String movieTitle);
}
