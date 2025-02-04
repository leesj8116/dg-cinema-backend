package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.Payment;
import devgraft.dgcinemabackend.reservation.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class PaymentAdapter implements PaymentRepository {
	private final PaymentJpaRepository paymentJpaRepository;

	@Override
	public Payment save(final Payment payment) {
		return paymentJpaRepository.save(payment);
	}

	@Override
	public List<Payment> findByReservationId(final Long reservationId) {
		return paymentJpaRepository.findByReservationId(reservationId);
	}
}
