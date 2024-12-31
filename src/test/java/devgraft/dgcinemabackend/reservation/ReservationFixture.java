package devgraft.dgcinemabackend.reservation;

import java.time.LocalDateTime;
import java.util.List;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.ScreenRoom;
import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.reservation.domain.Payment;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
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

	public static Reservation.ReservationBuilder anReservation() {
		return Reservation.builder()
			.reservationId(1L)
			.user(anDgUser().build())
			.runningTime(anRunningTime().build())
			.seatNo("A1");
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

	public static Payment.PaymentBuilder anPayment() {
		return Payment.builder()
			.paymentId(1L)
			.amount(10000)
			.reservation(anReservation().build())
			.result(Boolean.FALSE);
	}
}
