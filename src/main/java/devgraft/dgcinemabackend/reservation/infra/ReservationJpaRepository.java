package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findAllByRunningTime(final RunningTime runningTime);

	Reservation save(final Reservation entity);
}
