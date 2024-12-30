package devgraft.dgcinemabackend.runningtime;

import java.time.LocalDateTime;
import java.util.List;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.ScreenRoom;
import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

public class RunningTimeFixture {
	public static RunningTime.RunningTimeBuilder anRunningTime() {
		return RunningTime.builder()
			.runningTimeId(1L)
			.movie(anMovie().build())
			.screenRoom(anScreenRoom().build())
			.startTime(LocalDateTime.now());
	}

	public static ScreenRoom.ScreenRoomBuilder anScreenRoom() {
		return ScreenRoom.builder()
			.cinemaId(1L)
			.screenNumber(1L);
	}

	public static Cinema.CinemaBuilder anCinema() {
		return Cinema.builder()
			.cinemaId(1L)
			.screenRoomList(List.of(anScreenRoom().build()));
	}

	public static Movie.MovieBuilder anMovie() {
		return Movie.builder()
			.movieId(1L)
			.title("제목")
			.director("감독");
	}
}
