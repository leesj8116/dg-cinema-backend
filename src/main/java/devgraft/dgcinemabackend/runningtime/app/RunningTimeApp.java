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

	public List<RunningTime> getRunningTimesByMovieTitle(String movieTitle) {
		List<Movie> movies = movieFinder.findMoviesByMovieTitle(movieTitle);

		return runningTimeRepository.findAllByStartTimeGreaterThanEqualAndMovieIn(LocalDateTime.now(), movies);
	}
}
