package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.payment.domain.Payment;
import devgraft.dgcinemabackend.payment.domain.PaymentRepository;
import devgraft.dgcinemabackend.reservation.domain.PaymentRegister;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentRegisterImpl implements PaymentRegister {
	private final PaymentRepository paymentRepository;

	@Override
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}
}
