package devgraft.dgcinemabackend.reservation.domain;

import java.util.List;
import java.util.Optional;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;

public interface ReservationRepository {
	Optional<Reservation> findById(final Long id);

	List<String> findSeatNoByRunningTime(final RunningTime runningTime);

	Reservation save(final Reservation reservation);

	List<Reservation> findAllByUser(final DgUser user);

}
