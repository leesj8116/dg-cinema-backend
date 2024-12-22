package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReservationAdapter implements ReservationRepository {
	private final ReservationJpaRepository reservationJpaRepository;
	private final RunningTimeFinder runningTimeFinder;
	private final DgUserFinder dgUserFinder;

	@Override
	public List<Reservation> findAllByRunningTime(final Long runningTimeId) {
		RunningTime runningTime = runningTimeFinder.findById(runningTimeId)
			.orElseThrow(() -> new IllegalArgumentException("상영 시간 아이디가 유효하지 않습니다."));

		return reservationJpaRepository.findAllByRunningTime(runningTime);
	}

	@Override
	public Reservation save(final Reservation reservation) {
		return reservationJpaRepository.save(reservation);
	}
}
