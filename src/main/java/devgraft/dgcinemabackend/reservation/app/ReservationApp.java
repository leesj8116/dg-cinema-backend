package devgraft.dgcinemabackend.reservation.app;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationErrorCode;
import devgraft.dgcinemabackend.reservation.domain.ReservationException;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;
import devgraft.dgcinemabackend.reservation.domain.ReservationStatus;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class ReservationApp implements ReservationUseCase {
	private final ReservationRepository reservationRepository;
	private final RunningTimeFinder runningTimeFinder;
	private final DgUserFinder dgUserFinder;
	private final Set<ReservationStatus> usedStatus = Arrays.stream(new ReservationStatus[] {ReservationStatus.PENDING,
		ReservationStatus.SUCCESS}).collect(Collectors.toSet());

	public List<String> seatCheck(final Long runningTimeId) {
		final RunningTime runningTime = getRunningTime(runningTimeId);


		return reservationRepository.findSeatNoByRunningTimeAndStatusIn(runningTime, usedStatus);
	}

	@Transactional
	public ReservationResult register(final ReservationContext context) {
		// 1. 유저 검사
		final DgUser user = dgUserFinder.findById(context.userId())
			.orElseThrow(() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND));

		// 2. 상영 시간 검사
		final RunningTime runningTime = getRunningTime(context.runningTimeId());

		// 3. 좌석 검사
		reservationRepository.findSeatNoByRunningTimeAndStatusIn(runningTime, usedStatus)
			.stream()
			.filter(seatNo -> seatNo.equals(context.seatNo()))
			.findAny()
			.ifPresent(dummy -> {
				throw new ReservationException(ReservationErrorCode.ALREADY_SEAT_NO_HAS_RESERVED);
			});

		// 4. 예약 처리
		Reservation reservation = reservationRepository.save(Reservation.builder()
			.user(user)
			.runningTime(runningTime)
			.seatNo(context.seatNo())
			.build());

		return ReservationResult.from(reservation);
	}

	protected RunningTime getRunningTime(final Long runningTimeId) {
		return runningTimeFinder.findById(runningTimeId).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.RUNNING_TIME_NOT_FOUND)
		);
	}

	public List<ReservationResult> getUserReservations(final GetMyReservationRequest request) {
		DgUser user = dgUserFinder.findById(request.userId()).orElseThrow(
			() -> new ReservationException(ReservationErrorCode.USER_NOT_FOUND)
		);

		return reservationRepository.findAllByUser(user)
			.stream()
			.map(reservation -> ReservationResult.from(reservation))
			.toList();
	}
}
