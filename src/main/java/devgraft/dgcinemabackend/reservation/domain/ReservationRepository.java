package devgraft.dgcinemabackend.reservation.domain;

import java.util.List;

public interface ReservationRepository {
	List<Reservation> findAllByRunningTime(Long runningTimeId);

	Reservation save(ReservationContext context);
}
