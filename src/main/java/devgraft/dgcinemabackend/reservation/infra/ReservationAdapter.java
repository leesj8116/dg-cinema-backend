package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.ReservationStatus;
import devgraft.dgcinemabackend.reservation.domain.SeatNoMapping;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReservationAdapter implements ReservationRepository {
	private final ReservationJpaRepository reservationJpaRepository;

	@Override
	public Optional<Reservation> findById(final Long id) {
		return reservationJpaRepository.findById(id);
	}

	@Override
	public Reservation save(final Reservation reservation) {
		return reservationJpaRepository.save(reservation);
	}

	@Override
	public List<Reservation> findAllByUser(DgUser user) {
		return reservationJpaRepository.findAllByUser(user);
	}

	@Override
	public List<String> findSeatNoByRunningTimeAndStatusIn(RunningTime runningTime, Set<ReservationStatus> usedStatus) {
		return reservationJpaRepository.findSeatNoByRunningTimeAndStatusIn(runningTime, usedStatus)
			.stream()
			.map(SeatNoMapping::getSeatNo)
			.toList();
	}
}
