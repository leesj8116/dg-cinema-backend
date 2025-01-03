package devgraft.dgcinemabackend.runningtime.app;

import java.util.List;

public interface RunningTimeUseCase {
	List<RunningTimeResult> getRunningTimesByMovieTitle(final String movieTitle);
}
