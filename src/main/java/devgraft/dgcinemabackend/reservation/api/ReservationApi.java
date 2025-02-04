package devgraft.dgcinemabackend.reservation.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.reservation.app.ReservationUseCase;
import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.PaymentResult;
import devgraft.dgcinemabackend.reservation.domain.PurchaseContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationCancelContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ReservationApi {
	private final ReservationUseCase reservationUseCase;

	/**
	 * 예약된 좌석 목록을 반환한다.
	 * @return
	 */
	@GetMapping("/reservation/seat")
	public List<String> seatCheck(@RequestParam(name = "runningTime") Long runningTimeId) {
		return reservationUseCase.seatCheck(runningTimeId);
	}

	/**
	 * 예약을 진행한다
	 * @param context 예약에 필요한 정보 (누가, 어느 상영 시간을, 어떤 자리에)
	 * @return
	 */
	@PostMapping("/reservation")
	public ReservationResult register(@RequestBody ReservationContext context) {
		return reservationUseCase.register(context);
	}

	/**
	 * 사용자의 예약 목록을 반환한다.
	 * @param request userId
	 * @return
	 */
	@PostMapping("/reservation/my")
	public List<ReservationResult> getUserReservations(@RequestBody GetMyReservationRequest request) {
		return reservationUseCase.getUserReservations(request);
	}

	/**
	 * 예약 건에 대해 결제 처리한다.
	 * @param purchaseContext
	 * @return
	 */
	@PostMapping("/reservation/purchase")
	public PaymentResult register(@RequestBody PurchaseContext purchaseContext) {
		return reservationUseCase.purchase(purchaseContext);
	}

	/**
	 * 예약을 취소한다
	 * @param context 예약에 필요한 정보 (누가, 어느 상영 시간을, 어떤 자리에)
	 * @return
	 */
	@PostMapping("/reservation/cancel")
	public ReservationResult cancel(@RequestBody ReservationCancelContext context) {
		return reservationUseCase.cancelReservation(context);
	}
}
