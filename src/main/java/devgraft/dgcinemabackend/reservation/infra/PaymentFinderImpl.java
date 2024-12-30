package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.payment.domain.PaymentRepository;
import devgraft.dgcinemabackend.reservation.domain.PaymentFinder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class PaymentFinderImpl implements PaymentFinder {
	private final PaymentRepository paymentRepository;

}
