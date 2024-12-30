package devgraft.dgcinemabackend.payment.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.payment.domain.PaymentRepository;

@Component
class PaymentAdapter implements PaymentRepository {
	private final PaymentJpaRepository paymentJpaRepository;

	PaymentAdapter(PaymentJpaRepository paymentJpaRepository) {
		this.paymentJpaRepository = paymentJpaRepository;
	}

}
