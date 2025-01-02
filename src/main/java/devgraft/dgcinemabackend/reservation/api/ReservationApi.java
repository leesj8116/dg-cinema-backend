package devgraft.dgcinemabackend.reservation.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.reservation.app.ReservationApp;
import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;

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

	/**
	 * 예약을 진행한다
	 * @param context 예약에 필요한 정보 (누가, 어느 상영 시간을, 어떤 자리에)
	 * @return
	 */
	@PostMapping("/reservation")
	public ReservationResult register(@RequestBody ReservationContext context) {
		return reservationApp.register(context);
	}

	/**
	 * 사용자의 예약 목록을 반환한다.
	 * @param request userId
	 * @return
	 */
	@PostMapping("/reservation/my")
	public List<ReservationResult> getUserReservations(@RequestBody GetMyReservationRequest request) {
		return reservationApp.getUserReservations(request.userId());
	}
}
