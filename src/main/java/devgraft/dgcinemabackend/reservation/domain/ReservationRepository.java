package devgraft.dgcinemabackend.reservation.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;

public interface ReservationRepository {
	Optional<Reservation> findById(final Long id);

	Reservation save(final Reservation reservation);

	List<Reservation> findAllByUser(final DgUser user);

	List<String> findSeatNoByRunningTimeAndStatusIn(RunningTime runningTime, Set<ReservationStatus> usedStatus);
}
