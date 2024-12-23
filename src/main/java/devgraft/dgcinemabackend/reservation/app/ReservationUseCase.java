package devgraft.dgcinemabackend.reservation.app;

import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;

public interface ReservationUseCase {
	ReservationResult register(final ReservationContext context);
}
