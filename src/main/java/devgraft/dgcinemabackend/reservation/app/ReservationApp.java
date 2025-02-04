package devgraft.dgcinemabackend.reservation.app;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.Payment;
import devgraft.dgcinemabackend.reservation.domain.PaymentResult;
import devgraft.dgcinemabackend.reservation.domain.PaymentType;
import devgraft.dgcinemabackend.reservation.domain.PurchaseContext;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationCancelContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationErrorCode;
import devgraft.dgcinemabackend.reservation.domain.ReservationException;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;
import devgraft.dgcinemabackend.reservation.domain.ReservationStatus;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class ReservationApp implements ReservationUseCase {
	private final ReservationRepository reservationRepository;
	private final devgraft.dgcinemabackend.reservation.domain.PaymentRepository paymentRepository;
	private final RunningTimeFinder runningTimeFinder;
	private final DgUserFinder dgUserFinder;
	private final ExternalUseCase externalApp;

	private final Set<ReservationStatus> usedStatus = Arrays.stream(new ReservationStatus[] {ReservationStatus.PENDING,
		ReservationStatus.SUCCESS}).collect(Collectors.toSet());

	public List<String> seatCheck(final Long runningTimeId) {
		final RunningTime runningTime = getRunningTime(runningTimeId);


		return reservationRepository.findSeatNoByRunningTimeAndStatusIn(runningTime, usedStatus);
	}

	@Transactional
	@Override
	public ReservationResult register(final ReservationContext context) {
		// 1. 유저 검사
		final DgUser user = dgUserFinder.findById(context.userId())
			.orElseThrow(() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND));

		// 2. 상영 시간 검사
		final RunningTime runningTime = getRunningTime(context.runningTimeId());

		// 3. 좌석 검사
		reservationRepository.findSeatNoByRunningTimeAndStatusIn(runningTime, usedStatus)
			.stream()
			.filter(seatNo -> seatNo.equals(context.seatNo()))
			.findAny()
			.ifPresent(dummy -> {
				throw new ReservationException(ReservationErrorCode.ALREADY_SEAT_NO_HAS_RESERVED);
			});

		// 4. 예약 처리
		Reservation reservation = reservationRepository.save(Reservation.builder()
			.user(user)
			.runningTime(runningTime)
			.seatNo(context.seatNo())
			.build());

		return ReservationResult.from(reservation);
	}

	@Override
	public PaymentResult purchase(PurchaseContext purchaseContext) {
		// 1. 예약 조회
		Reservation reservation = reservationRepository.findById(purchaseContext.reservationId())
			.orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVATION_NOT_FOUND));

		// 2. 예약 유효성 및 정상 동작 검사
		//// 2-1. 예약 생성 후 5분 이후에 결제를 시도할 경우 에러 반환
		LocalDateTime createdDate = reservation.getCreatedDate();
		if (LocalDateTime.now().isAfter(createdDate.plusMinutes(5L))) {
			// 예약 후 5분이 지났을 경우
			reservation.modifyStatus(ReservationStatus.EXPIRED);	// 예약 만료로 변경
			reservationRepository.save(reservation);				// 예약 상태 변경 반영

			throw new ReservationException(ReservationErrorCode.ALREADY_RESERVATION_HAS_NOT_AVAILABLE);
		}
		//// 2-2. 결제 대기 상태가 아닌 예약에 대해 처리할 경우 (중복 결제 또는 취소로 인해 만료된 예약 건)
		if (!reservation.getStatus().equals(ReservationStatus.PENDING)) {
			throw new ReservationException(ReservationErrorCode.ALREADY_RESERVATION_HAS_NOT_AVAILABLE);
		}

		// 3. 외부 결제 API에 대해 결제 요청
		Payment payment = null;
		Boolean externalResult = Boolean.FALSE;
		try {
			externalResult = externalApp.purchase();	// 외부 결제 모듈에 결제 요청 및 완료 (더미 흉내)
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ReservationException(ReservationErrorCode.EXTERNAL_PAYMENT_API_ERROR);
		} finally {
			payment = paymentRepository.save(Payment.builder()
				.reservation(reservation)
				.amount(purchaseContext.amount())
				.type(PaymentType.PURCHASE)
				.success(externalResult)
				.build());
		}

		if (externalResult) {
			reservation.modifyStatus(ReservationStatus.SUCCESS);
			reservationRepository.save(reservation);
		}

		return PaymentResult.of(payment.getPaymentId(), payment.getAmount(), payment.getSuccess());
	}

	@Override
	public List<ReservationResult> getUserReservations(final GetMyReservationRequest request) {
		DgUser user = dgUserFinder.findById(request.userId()).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND)
		);

		return reservationRepository.findAllByUser(user)
			.stream()
			.map(reservation -> ReservationResult.from(reservation))
			.toList();
	}

	@Transactional
	@Override
	public ReservationResult cancelReservation(ReservationCancelContext context) {
		// 1. 유저 검사
		final DgUser user = dgUserFinder.findById(context.userId())
			.orElseThrow(() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND));

		// 2. 예약 조회
		final Reservation reservation = reservationRepository.findById(context.reservationId()).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.RESERVAION_NOT_FOUND)
		);

		// 3. 예약자와 예약 주인 일치 확인
		if (!reservation.getUser().getUserId().equals(user.getUserId())) {
			throw new ReservationException(ReservationErrorCode.PARAMETER_IS_NOT_VALID);
		}

		// 4. 취소 가능 여부 확인
		// 4-(1) 이미 취소된 예약의 경우
		if (!usedStatus.contains(reservation.getStatus())) {
			throw new ReservationException(ReservationErrorCode.ALREADY_RESERVATION_HAS_NOT_AVAILABLE);
		}

		// 4-(2) 상영 시작 10분 전을 지났을 경우
		if (LocalDateTime.now().isAfter(reservation.getRunningTime().getStartTime().minusMinutes(5L))) {
			throw new ReservationException(ReservationErrorCode.START_TIME_HAS_ALMOST_CLOSED);
		}

		// 5. 결제 API 취소 요청
		final Payment beforePayment = paymentRepository.findByReservationId(reservation.getReservationId()).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.PAYMENT_NOT_FOUND)
		);

		Payment.PaymentBuilder paymentBuilder = Payment.builder()
			.type(PaymentType.REFUND)
			.amount(beforePayment.getAmount())
			.success(Boolean.FALSE)
			.reservation(reservation);

		try {
			// 외부 API 흉내만
			paymentBuilder.success(externalApp.refund());
		} catch (RuntimeException e) {
			e.printStackTrace();
			paymentBuilder.success(Boolean.FALSE);
		}

		paymentRepository.save(paymentBuilder.build());

		// 6. 예약 취소 처리
		reservation.modifyStatus(ReservationStatus.CANCEL);
		reservationRepository.save(reservation);

		return ReservationResult.from(reservation);
	}

	protected RunningTime getRunningTime(final Long runningTimeId) {
		return runningTimeFinder.findById(runningTimeId).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.RUNNING_TIME_NOT_FOUND)
		);
	}
}
