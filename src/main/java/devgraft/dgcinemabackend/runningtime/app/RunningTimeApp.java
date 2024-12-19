package devgraft.dgcinemabackend.runningtime.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.MovieFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;

@Service
public class RunningTimeApp {
	private final RunningTimeRepository runningTimeRepository;
	private final MovieFinder movieFinder;

	public RunningTimeApp(RunningTimeRepository runningTimeRepository, MovieFinder movieFinder) {
		this.runningTimeRepository = runningTimeRepository;
		this.movieFinder = movieFinder;
	}

	public List<RunningTime> getRunningTimesByMovieTitle(String movieTitle) {
		// List<Movie> movies = (movieTitle == null || movieTitle.isBlank()) ? movieFinder.findAllMovies() : movieFinder.findMoviesByMovieTitle(movieTitle);
		List<Movie> movies = movieFinder.findMoviesByMovieTitle(movieTitle);

		List<RunningTime> runningTimes = new ArrayList<>();

		for(Movie movie : movies) {
			runningTimes.addAll(runningTimeRepository.findAllByStartTimeGreaterThanEqualAndMovie(LocalDateTime.now(), movie));
		}

		return runningTimes;
	}
}
