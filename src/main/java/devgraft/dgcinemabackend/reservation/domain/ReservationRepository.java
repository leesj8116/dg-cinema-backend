package devgraft.dgcinemabackend.reservation.domain;

import java.util.List;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

public interface ReservationRepository {
	List<String> findSeatNoByRunningTime(final RunningTime runningTime);

	Reservation save(final Reservation reservation);
}
