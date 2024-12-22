package devgraft.dgcinemabackend.reservation.domain;

import java.util.List;

public interface ReservationRepository {
	List<Reservation> findAllByRunningTime(final Long runningTimeId);

	Reservation save(final Reservation reservation);
}
