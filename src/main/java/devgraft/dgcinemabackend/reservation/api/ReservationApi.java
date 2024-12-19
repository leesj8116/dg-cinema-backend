package devgraft.dgcinemabackend.reservation.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.reservation.app.ReservationApp;

@RestController
public class ReservationApi {
	private final ReservationApp reservationApp;

	public ReservationApi(ReservationApp reservationApp) {
		this.reservationApp = reservationApp;
	}

	/**
	 * 예약된 좌석 목록을 반환한다.
	 * @return
	 */
	@GetMapping("/reservation/seat")
	public List<String> seatCheck(@RequestParam(name = "runningTime") Long runningTimeId) {
		return reservationApp.seatCheck(runningTimeId);
	}
}
