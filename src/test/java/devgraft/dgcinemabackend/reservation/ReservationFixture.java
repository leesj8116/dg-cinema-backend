package devgraft.dgcinemabackend.reservation;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;

public class ReservationFixture {
	public static DgUser.DgUserBuilder anDgUser() {
		return DgUser.builder()
			.userId(1L);
	}

	public static RunningTime.RunningTimeBuilder anRunningTime() {
		return RunningTime.builder()
			.runningTimeId(1L);
	}

	public static Reservation.ReservationBuilder anReservation() {
		return Reservation.builder()
			.reservationId(1L)
			.user(anDgUser().build())
			.runningTime(anRunningTime().build())
			.seetNo("A1");
	}

}
