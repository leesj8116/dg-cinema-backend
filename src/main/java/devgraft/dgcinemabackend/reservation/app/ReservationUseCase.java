package devgraft.dgcinemabackend.reservation.app;

import java.util.List;

import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.PaymentResult;
import devgraft.dgcinemabackend.reservation.domain.PurchaseContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationCancelContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;

public interface ReservationUseCase {
	List<String> seatCheck(final Long runningTimeId);

	ReservationResult register(final ReservationContext context);

	PaymentResult purchase(final PurchaseContext purchaseContext);

	List<ReservationResult> getUserReservations(final GetMyReservationRequest request);

	ReservationResult cancelReservation(final ReservationCancelContext context);
}
