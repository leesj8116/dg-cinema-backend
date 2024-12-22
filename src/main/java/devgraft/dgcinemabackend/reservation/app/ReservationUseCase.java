package devgraft.dgcinemabackend.reservation.app;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;

public interface ReservationUseCase {
	Reservation register(final ReservationContext context);
}
