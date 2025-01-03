package devgraft.dgcinemabackend.payment.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.payment.domain.ReservationRegister;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReservationRegisterImpl implements ReservationRegister {
	private final ReservationRepository reservationRepository;

	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}
}
