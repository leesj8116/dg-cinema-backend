package devgraft.dgcinemabackend.reservation.app;

import java.util.List;

import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;

public interface ReservationUseCase {
	List<String> seatCheck(final Long runningTimeId);

	ReservationResult register(final ReservationContext context);

	List<ReservationResult> getUserReservations(final GetMyReservationRequest request);
}
