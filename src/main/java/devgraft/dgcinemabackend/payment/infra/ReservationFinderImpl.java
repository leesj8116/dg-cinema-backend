package devgraft.dgcinemabackend.payment.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.payment.domain.ReservationFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReservationFinderImpl implements ReservationFinder {
	private final ReservationRepository reservationRepository;

	@Override
	public Optional<Reservation> findById(Long id) {
		return reservationRepository.findById(id);
	}
}
