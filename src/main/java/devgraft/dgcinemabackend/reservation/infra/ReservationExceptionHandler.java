package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import devgraft.dgcinemabackend.reservation.domain.ReservationException;

@RestControllerAdvice
class ReservationExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<Object> handleReservationException(final ReservationException e) {
		e.printStackTrace();

		ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}
}
