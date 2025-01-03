package devgraft.dgcinemabackend.payment.domain;

import java.util.Optional;

import devgraft.dgcinemabackend.reservation.domain.Reservation;

public interface ReservationFinder {
	Optional<Reservation> findById(final Long id);
}
