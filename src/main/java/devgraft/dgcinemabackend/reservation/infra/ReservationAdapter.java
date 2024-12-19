package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

@Component
class ReservationAdapter implements ReservationRepository {
	private final ReservationJpaRepository reservationJpaRepository;
	private final RunningTimeFinder runningTimeFinder;

	ReservationAdapter(ReservationJpaRepository reservationJpaRepository, RunningTimeFinder runningTimeFinder) {
		this.reservationJpaRepository = reservationJpaRepository;
		this.runningTimeFinder = runningTimeFinder;
	}

	@Override
	public List<Reservation> findAllByRunningTime(Long runningTimeId) {
		RunningTime runningTime = runningTimeFinder.findById(runningTimeId)
			.orElseThrow(() -> new IllegalArgumentException("상영 시간 아이디가 유효하지 않습니다."));

		return reservationJpaRepository.findAllByRunningTime(runningTime);
	}
}
