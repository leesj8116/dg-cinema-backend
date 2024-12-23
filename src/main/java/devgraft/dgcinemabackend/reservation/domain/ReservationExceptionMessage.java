package devgraft.dgcinemabackend.reservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationExceptionMessage {
	// @TODO: Exception 을 구현하는 게 나은데, 일단 메세지만 관리

	USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
	RUNNING_TIME_NOT_FOUND("상영 시간 정보를 찾을 수 없습니다."),
	CINEMA_NOT_FOUND("극장 정보를 찾을 수 없습니다."),
	ALREADY_SEAT_NO_HAS_RESERVED("이미 예약된 좌석입니다.");

	private final String message;
}
