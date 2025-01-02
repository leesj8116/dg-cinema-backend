package devgraft.dgcinemabackend.reservation.domain;

import org.springframework.http.HttpStatus;

import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationErrorCode implements ErrorCode {
	LOGIN_FIRST(HttpStatus.UNAUTHORIZED, "로그인한 사용자만 사용 가능합니다."),
	PARAMETER_IS_NOT_VALID(HttpStatus.BAD_REQUEST, "입력 값이 올바르지 않습니다."),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
	RUNNING_TIME_NOT_FOUND(HttpStatus.BAD_REQUEST, "상영 시간 정보를 찾을 수 없습니다."),
	CINEMA_NOT_FOUND(HttpStatus.BAD_REQUEST, "극장 정보를 찾을 수 없습니다."),
	ALREADY_SEAT_NO_HAS_RESERVED(HttpStatus.BAD_REQUEST, "이미 예약된 좌석입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
