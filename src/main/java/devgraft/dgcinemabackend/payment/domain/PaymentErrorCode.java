package devgraft.dgcinemabackend.payment.domain;

import org.springframework.http.HttpStatus;

import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode implements ErrorCode {
	RESERVATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "예약 이력을 찾을 수 없습니다"),
	RESERVATION_IS_EXPIRED(HttpStatus.BAD_REQUEST, "이미 만료된 예약입니다");

	private final HttpStatus httpStatus;
	private final String message;
}
