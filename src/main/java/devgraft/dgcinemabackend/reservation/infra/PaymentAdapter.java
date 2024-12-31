package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.Payment;
import devgraft.dgcinemabackend.reservation.domain.PaymentRepository;

@Component
class PaymentAdapter implements PaymentRepository {
	private final PaymentJpaRepository paymentJpaRepository;

	PaymentAdapter(PaymentJpaRepository paymentJpaRepository) {
		this.paymentJpaRepository = paymentJpaRepository;
	}

	@Override
	public Payment save(Payment payment) {
		return paymentJpaRepository.save(payment);
	}
}
