package devgraft.dgcinemabackend.reservation.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.reservation.domain.CinemaFinder;
import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationExceptionMessage;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationApp implements ReservationUseCase {
	private final ReservationRepository reservationRepository;
	private final DgUserFinder dgUserFinder;
	private final RunningTimeFinder runningTimeFinder;
	private final CinemaFinder cinemaFinder;

	public List<String> seatCheck(final Long runningTimeId) {
		// @TODO: 예약 현황을 확인하려면 RunningTime domain에 접근해야함.. 이럴 때는 어떻게 둘을 분리할 수 있을까?
		return getReservations(runningTimeId)
			.stream()
			.map(reservation -> reservation.getSeatNo())
			.collect(Collectors.toList());
	}

	public ReservationResult register(final ReservationContext context) {
		// Long userId, Long runningTimeId, String settNo
		// user가 존재하는지 검사
		// runningTimeId가 존재하는지 검사
		// seetNo가 이미 예약되어잇는지 검사

		// 1. 유저 검사
		final DgUser user = dgUserFinder.findById(context.userId())
			.orElseThrow(() -> new IllegalArgumentException(ReservationExceptionMessage.USER_NOT_FOUND.getMessage()));

		// 2. 상영 시간 검사
		final RunningTime runningTime = runningTimeFinder.findById(context.runningTimeId())
			.orElseThrow(() -> new IllegalArgumentException(ReservationExceptionMessage.RUNNING_TIME_NOT_FOUND.getMessage()));

		// 3. 좌석 검사
		getReservations(runningTime.getRunningTimeId())
			.stream()
			.map(reservation -> reservation.getSeatNo())
			.filter(seatNo -> seatNo.equals(context.seatNo()))
			.findAny()
			.ifPresent(dummy -> {
				throw new IllegalArgumentException(ReservationExceptionMessage.ALREADY_SEAT_NO_HAS_RESERVED.getMessage());
			});

		// 4. 예약 처리
		Reservation result = reservationRepository.save(Reservation.builder()
			.user(user)
			.runningTime(runningTime)
			.seatNo(context.seatNo())
			.build());

		// 5. 극장 조회 (검증 X, 결과 반환 위해 사용)
		Cinema cinema = cinemaFinder.findById(runningTime.getScreenRoom().getCinemaId()).orElseThrow(
			() -> new IllegalArgumentException(ReservationExceptionMessage.CINEMA_NOT_FOUND.getMessage()));

		return new ReservationResult(
			result.getReservationId(),user.getNickname(), runningTime.getStartTime(),
			runningTime.getMovie().getTitle(), cinema.getName(), runningTime.getScreenRoom().getScreenNumber(),
			result.getSeatNo());
	}

	/**
	 * 상영시간 내 예약을 모두 조회한다.
	 * @param runningTimeId
	 * @return
	 */
	private List<Reservation> getReservations(final Long runningTimeId) {
		return reservationRepository.findAllByRunningTime(runningTimeId);
	}
}
