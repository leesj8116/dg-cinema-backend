package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.reservation.domain.Reservation;

interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
