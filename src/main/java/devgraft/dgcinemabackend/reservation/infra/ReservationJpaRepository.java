package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationStatus;
import devgraft.dgcinemabackend.reservation.domain.SeatNoMapping;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;

interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
	Reservation save(final Reservation reservation);

	List<Reservation> findAllByUser(final DgUser user);

	List<SeatNoMapping> findSeatNoByRunningTimeAndStatusIn(RunningTime runningTime, Set<ReservationStatus> usedStatus);
}
