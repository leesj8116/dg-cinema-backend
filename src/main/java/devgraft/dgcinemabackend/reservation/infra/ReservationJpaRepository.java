package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.SeatNoMapping;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
	List<SeatNoMapping> findSeatNoByRunningTime(final RunningTime runningTime);

	Reservation save(final Reservation reservation);
}
