package devgraft.dgcinemabackend.reservation.domain;

import java.time.LocalDateTime;

public record ReservationResult(
	Long reservationId,				// 예약 아이디
	String userName,				// 예약자 이름
	LocalDateTime startTime,		// 상영 시작 시간
	String movieTitle,				// 영화 이름
	String cinemaName,				// 극장 이름
	Long screenNumber,				// 상영관 번호
	String seatNo					// 좌석 번호
) {
}
