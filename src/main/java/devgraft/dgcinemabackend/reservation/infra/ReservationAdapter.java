package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;

@Component
class ReservationAdapter implements ReservationRepository {
	private final ReservationJpaRepository reservationJpaRepository;

	ReservationAdapter(ReservationJpaRepository reservationJpaRepository) {
		this.reservationJpaRepository = reservationJpaRepository;
	}

}
