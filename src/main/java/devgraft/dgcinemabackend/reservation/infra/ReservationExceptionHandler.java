package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.reservation.domain.ReservationException;

@RestControllerAdvice
class ReservationExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<Object> handleReservationException(final ReservationException e) {
		e.printStackTrace();

		return handleExceptionInternal(e.getErrorCode(), e.getMessage());
	}
}
