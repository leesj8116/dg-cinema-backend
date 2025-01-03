package devgraft.dgcinemabackend.payment.domain;

import devgraft.dgcinemabackend.reservation.domain.Reservation;

public interface ReservationRegister {
	Reservation save(final Reservation reservation);
}
