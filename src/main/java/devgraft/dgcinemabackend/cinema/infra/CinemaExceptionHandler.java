package devgraft.dgcinemabackend.cinema.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.cinema.exception.CinemaException;
import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.common.exception.ErrorCode;

@RestControllerAdvice
class CinemaExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(CinemaException.class)
	public ResponseEntity<Object> handleCinemaException(CinemaException e) {
		e.printStackTrace();

		ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}
}
