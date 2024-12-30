package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.payment.domain.Payment;

public interface PaymentRegister {
	Payment save(Payment payment);
}
