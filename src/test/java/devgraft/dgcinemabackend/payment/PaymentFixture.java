package devgraft.dgcinemabackend.payment;

import devgraft.dgcinemabackend.payment.domain.Payment;
import devgraft.dgcinemabackend.payment.domain.PaymentType;
import devgraft.dgcinemabackend.reservation.domain.Reservation;

public class PaymentFixture {
	public static Payment.PaymentBuilder anPayment() {
		return Payment.builder()
			.paymentId(1L)
			.amount(10000)
			.type(PaymentType.PURCHASE)
			.success(true)
			.reservation(anReservation().build());
	}

	public static Reservation.ReservationBuilder anReservation() {
		return Reservation.builder().reservationId(1L);
	}
}
