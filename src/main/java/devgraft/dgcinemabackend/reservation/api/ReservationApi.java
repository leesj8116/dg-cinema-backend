package devgraft.dgcinemabackend.reservation.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.reservation.app.ReservationApp;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;

@RestController
public class ReservationApi {
	private final ReservationApp reservationApp;
	private final DgUserRepository dgUserRepository;

	public ReservationApi(ReservationApp reservationApp, DgUserRepository dgUserRepository) {
		this.reservationApp = reservationApp;
		this.dgUserRepository = dgUserRepository;
	}

	/**
	 * 예약된 좌석 목록을 반환한다.
	 * @return
	 */
	@GetMapping("/reservation/seat")
	public List<String> seatCheck(@RequestParam(name = "runningTime") Long runningTimeId) {
		return reservationApp.seatCheck(runningTimeId);
	}

	public Reservation register(ReservationContext context) {
		// return reservationApp.register(context);
		dgUserRepository.findById(context.userId())
			.orElseThrow(() -> new IllegalArgumentException("User does not exist"));

		return null;
	}
}
