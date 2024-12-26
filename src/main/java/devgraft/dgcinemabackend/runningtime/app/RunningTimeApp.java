package devgraft.dgcinemabackend.runningtime.app;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.MovieFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RunningTimeApp {
	private final RunningTimeRepository runningTimeRepository;
	private final MovieFinder movieFinder;

	/**
	 * 영화 제목을 이용하여 상영일정을 조회한다.
	 * 이 때, 아직 상영시작 전인 영화만 조회한다.
	 * @param movieTitle
	 * @return
	 */
	public List<RunningTime> getRunningTimesByMovieTitle(String movieTitle) {
		List<Movie> movies = movieFinder.findMoviesByMovieTitle(movieTitle);

		return runningTimeRepository.findAllByStartTimeGreaterThanEqualAndMovieIn(LocalDateTime.now(), movies);
	}
}
