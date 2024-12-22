package devgraft.dgcinemabackend.reservation.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationApp implements ReservationUseCase {
	private final ReservationRepository reservationRepository;
	private final DgUserFinder dgUserFinder;

	public List<String> seatCheck(final Long runningTimeId) {
		// @TODO: 예약 현황을 확인하려면 RunningTime domain에 접근해야함.. 이럴 때는 어떻게 둘을 분리할 수 있을까?
		List<Reservation> reservationList = reservationRepository.findAllByRunningTime(runningTimeId);

		return reservationList.stream().map(reservation -> reservation.getSeetNo()).collect(Collectors.toList());
	}

	public Reservation register(final ReservationContext context) {
		// Long userId, Long runningTimeId, String settNo
		// user가 존재하는지 검사
		// runningTimeId가 존재하는지 검사
		// seetNo가 이미 예약되어잇는지 검사
		
		dgUserFinder.findById(context.userId()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

		throw new IllegalArgumentException();
	}
}
