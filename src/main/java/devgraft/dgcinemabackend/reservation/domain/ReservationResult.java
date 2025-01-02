package devgraft.dgcinemabackend.reservation.domain;

import java.time.LocalDateTime;

public record ReservationResult(
	Long reservationId,         // 예약 아이디
	String userName,            // 예약자 이름
	LocalDateTime startTime,    // 상영 시작 시간
	String movieTitle,          // 영화 이름
	Long cinemaId,              // 극장 아이디
	Long screenNumber,          // 상영관 번호
	String seatNo,              // 좌석 번호
	String status,              // 결제 상태
	LocalDateTime createdDate   // 예약 생성 시간
) {
	public static ReservationResult from(Reservation reservation) {
		return new ReservationResult(
			reservation.getReservationId(),
			reservation.getUser().getNickname(),
			reservation.getRunningTime().getStartTime(),
			reservation.getRunningTime().getMovie().getTitle(),
			reservation.getRunningTime().getScreenRoom().getCinemaId(),
			reservation.getRunningTime().getScreenRoom().getScreenNumber(),
			reservation.getSeatNo(),
			reservation.getStatus().getDescription(),
			reservation.getCreatedDate()
		);
	}
}
