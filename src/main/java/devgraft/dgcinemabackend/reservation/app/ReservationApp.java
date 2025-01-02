package devgraft.dgcinemabackend.reservation.app;

import java.util.List;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.reservation.domain.CinemaFinder;
import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Payment;
import devgraft.dgcinemabackend.reservation.domain.PaymentRepository;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationErrorCode;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.reservation.exception.ReservationException;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationApp implements ReservationUseCase {
	private final ReservationRepository reservationRepository;
	private final PaymentRepository paymentRepository;
	private final RunningTimeFinder runningTimeFinder;
	private final DgUserFinder dgUserFinder;
	private final CinemaFinder cinemaFinder;

	public List<String> seatCheck(final Long runningTimeId) {
		log.info("예약된 좌석 조회", runningTimeId);
		final RunningTime runningTime = getRunningTime(runningTimeId);
		return reservationRepository.findSeatNoByRunningTime(runningTime);
	}

	@Transactional
	public ReservationResult register(final ReservationContext context) {
		log.info("예약 진행", context);
		// user가 존재하는지 검사
		// runningTimeId가 존재하는지 검사
		// seetNo가 이미 예약되어잇는지 검사

		// 1. 유저 검사
		final DgUser user = dgUserFinder.findById(context.userId())
			.orElseThrow(() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND));

		// 2. 상영 시간 검사
		final RunningTime runningTime = getRunningTime(context.runningTimeId());

		// 3. 좌석 검사
		reservationRepository.findSeatNoByRunningTime(runningTime)
			.stream()
			.filter(seatNo -> seatNo.equals(context.seatNo()))
			.findAny()
			.ifPresent(dummy -> {
				throw new ReservationException(ReservationErrorCode.ALREADY_SEAT_NO_HAS_RESERVED);
			});

		// 4. 예약 처리
		Reservation result = reservationRepository.save(Reservation.builder()
			.user(user)
			.runningTime(runningTime)
			.seatNo(context.seatNo())
			.build());

		// 5. 극장 조회 (검증 X, 결과 반환 위해 사용)
		final Cinema cinema = cinemaFinder.findById(runningTime.getScreenRoom().getCinemaId()).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.CINEMA_NOT_FOUND)
		);

		// 6. 결제 생성
		final Payment payment = paymentRepository.save(Payment.builder()
			.amount(10000)  // 한 장 당 10000원 단일
			.reservation(result)
			.result(Boolean.FALSE)
			.build());

		return new ReservationResult(
			result.getReservationId(), user.getNickname(), runningTime.getStartTime(),
			runningTime.getMovie().getTitle(), cinema.getName(), runningTime.getScreenRoom().getScreenNumber(),
			result.getSeatNo(), payment.getPaymentId());
	}

	protected RunningTime getRunningTime(final Long runningTimeId) {
		return runningTimeFinder.findById(runningTimeId).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.RUNNING_TIME_NOT_FOUND)
		);
	}

	public List<ReservationResult> getUserReservations(final Long userId) {
		if (userId == null) {
			throw new ReservationException(CommonErrorCode.INVALID_PARAMETER);
		}

		DgUser user = dgUserFinder.findById(userId).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND)
		);

		return null;
	}
}
