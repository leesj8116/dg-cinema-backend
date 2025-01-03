package devgraft.dgcinemabackend.reservation;

import java.time.LocalDateTime;

import devgraft.dgcinemabackend.cinema.domain.ScreenRoom;
import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;

public class ReservationFixture {
	public static DgUser.DgUserBuilder anDgUser() {
		return DgUser.builder()
			.userId(1L)
			.nickname("홍길동");
	}

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

	public static Movie.MovieBuilder anMovie() {
		return Movie.builder()
			.movieId(1L)
			.title("제목")
			.director("감독");
	}
}
