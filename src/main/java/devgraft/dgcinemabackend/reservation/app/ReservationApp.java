package devgraft.dgcinemabackend.reservation.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationApp {
	private final ReservationRepository reservationRepository;

	public ReservationApp(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<String> seatCheck(Long runningTimeId) {
		// @TODO: 예약 현황을 확인하려면 RunningTime domain에 접근해야함.. 이럴 때는 어떻게 둘을 분리할 수 있을까?
		List<Reservation> reservationList = reservationRepository.findAllByRunningTime(runningTimeId);


		return reservationList.stream().map(reservation -> reservation.getSeetNo()).collect(Collectors.toList());
	}
}
