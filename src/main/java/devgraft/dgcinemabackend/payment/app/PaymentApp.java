package devgraft.dgcinemabackend.payment.app;

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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentApp implements PaymentUseCase {
	private final PaymentRepository paymentRepository;
	private final ReservationFinder reservationFinder;
	private final ReservationRegister reservationRegister;

	@Override
	@Transactional
	public PaymentResult purchase(final PurchaseContext purchaseContext, final Boolean result, final String message) {
		// 1. 예약 조회
		Reservation reservation = reservationFinder.findById(purchaseContext.reservationId())
			.orElseThrow(() -> new PaymentException(PaymentErrorCode.RESERVATION_NOT_FOUND));

		// 2. 결제 저장
		Payment payment = paymentRepository.save(Payment.builder()
			.reservation(Reservation.builder().reservationId(purchaseContext.reservationId()).build())
			.amount(10000)
			.type(PaymentType.PURCHASE)
			.success(result)
			.build());

		// 3. 예약 결과를 '결제 완료'로 변경
		reservation.modifyStatus(ReservationStatus.SUCCESS);

		// 4. 업데이트 된 예약을 DB에 반영
		reservationRegister.save(reservation);

		// 5. 결과 반환
		return PaymentResult.of(
			payment.getPaymentId(), payment.getAmount(),
			payment.getSuccess(), message
		);
	}
}
