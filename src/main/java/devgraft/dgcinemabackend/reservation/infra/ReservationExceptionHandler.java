package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import devgraft.dgcinemabackend.reservation.exception.ReservationException;
import lombok.extern.slf4j.Slf4j;

/**
 * https://velog.io/@woosim34/RestControllerAdvice를-이용한-예외처리
 * 을 참조하여 ExceptionHandler 구현
 */
@Slf4j
@RestControllerAdvice
class ReservationExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<Object> handleReservationException(final ReservationException e) {
		e.printStackTrace();

		ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}
}
