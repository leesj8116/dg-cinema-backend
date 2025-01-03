package devgraft.dgcinemabackend.runningtime.app;

import java.time.LocalDateTime;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

public record RunningTimeResult(
	Long runningTimeId,
	Long movieId,
	String movieTitle,
	LocalDateTime startTime,
	Long cinemaId,
	Long screenNumber
) {
	public static RunningTimeResult from(RunningTime runningTime) {
		return new RunningTimeResult(
			runningTime.getRunningTimeId(),
			runningTime.getMovie().getMovieId(),
			runningTime.getMovie().getTitle(),
			runningTime.getStartTime(),
			runningTime.getScreenRoom().getCinemaId(),
			runningTime.getScreenRoom().getScreenNumber()
		);
	}
}
