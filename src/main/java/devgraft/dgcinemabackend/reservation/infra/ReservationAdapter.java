package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;

@Component
class ReservationAdapter implements ReservationRepository {
	private final ReservationJpaRepository reservationJpaRepository;
	private final RunningTimeFinder runningTimeFinder;
	private final DgUserFinder dgUserFinder;

	ReservationAdapter(ReservationJpaRepository reservationJpaRepository, RunningTimeFinder runningTimeFinder,
		DgUserFinder dgUserFinder) {
		this.reservationJpaRepository = reservationJpaRepository;
		this.runningTimeFinder = runningTimeFinder;
		this.dgUserFinder = dgUserFinder;
	}

	@Override
	public List<Reservation> findAllByRunningTime(Long runningTimeId) {
		RunningTime runningTime = runningTimeFinder.findById(runningTimeId)
			.orElseThrow(() -> new IllegalArgumentException("상영 시간 아이디가 유효하지 않습니다."));

		return reservationJpaRepository.findAllByRunningTime(runningTime);
	}

	@Override
	public Reservation save(ReservationContext context) {
		// user 유효성 확인
		DgUser user = dgUserFinder.findById(context.userId()).orElseThrow(() -> new NoSuchElementException("사용자가 존재하지 않습니다."));

		// @TODO: 퇴근 전 커밋, 이어서 구현

		return reservationJpaRepository.save(new Reservation());
	}
}
