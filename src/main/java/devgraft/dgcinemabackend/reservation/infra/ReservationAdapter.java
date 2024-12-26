package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.SeatNoMapping;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReservationAdapter implements ReservationRepository {
	private final ReservationJpaRepository reservationJpaRepository;

	@Override
	public List<String> findSeatNoByRunningTime(RunningTime runningTime) {
		return reservationJpaRepository.findSeatNoByRunningTime(runningTime)
			.stream()
			.map(SeatNoMapping::getSeatNo)
			.toList();
	}

	@Override
	public Reservation save(final Reservation reservation) {
		return reservationJpaRepository.save(reservation);
	}
}
