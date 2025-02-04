package devgraft.dgcinemabackend.payment.app;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.payment.domain.Payment;
import devgraft.dgcinemabackend.payment.domain.PaymentErrorCode;
import devgraft.dgcinemabackend.payment.domain.PaymentException;
import devgraft.dgcinemabackend.payment.domain.PaymentRepository;
import devgraft.dgcinemabackend.payment.domain.PaymentResult;
import devgraft.dgcinemabackend.payment.domain.PaymentType;
import devgraft.dgcinemabackend.payment.domain.PurchaseContext;
import devgraft.dgcinemabackend.payment.domain.ReservationFinder;
import devgraft.dgcinemabackend.payment.domain.ReservationRegister;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentApp implements PaymentUseCase {
	private final ExternalUseCase externalUseCase;
	private final PaymentRepository paymentRepository;
	private final ReservationFinder reservationFinder;
	private final ReservationRegister reservationRegister;

	@Override
	public PaymentResult purchase(final PurchaseContext purchaseContext) {
		// 1. 예약 조회
		Reservation reservation = reservationFinder.findById(purchaseContext.reservationId())
			.orElseThrow(() -> new PaymentException(PaymentErrorCode.RESERVATION_NOT_FOUND));

		// 2. 예약 유효성 및 정상 동작 검사
		//// 2-1. 예약 생성 후 5분 이후에 결제를 시도할 경우 에러 반환
		LocalDateTime createdDate = reservation.getCreatedDate();
		if (LocalDateTime.now().isAfter(createdDate.plusMinutes(5L))) {
			throw new PaymentException(PaymentErrorCode.RESERVATION_IS_EXPIRED);
		}
		//// 2-2. 결제 대기 상태가 아닌 예약에 대해 처리할 경우 (중복 결제 또는 취소로 인해 만료된 예약 건)
		if (!reservation.getStatus().equals(ReservationStatus.PENDING)) {
			throw new PaymentException(PaymentErrorCode.RESERVATION_IS_ALREADY_EXECUTED);
		}

		// 3. 외부 결제 API에 대해 결제 요청
		Payment payment = null;
		Boolean externalResult = Boolean.FALSE;
		try {
			externalResult = externalUseCase.purchase();	// 외부 결제 모듈에 결제 요청 및 완료 (더미 흉내)
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new PaymentException(PaymentErrorCode.EXTERNAL_PAYMENT_ERROR);
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
			reservationRegister.save(reservation);
		}

		return PaymentResult.of(payment.getPaymentId(), payment.getAmount(), payment.getSuccess());
	}
}
