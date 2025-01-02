package devgraft.dgcinemabackend.movie.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import devgraft.dgcinemabackend.movie.exception.MovieException;

@RestControllerAdvice
class MovieExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(MovieException.class)
	public ResponseEntity<Object> handleMovieException(final MovieException e) {
		e.printStackTrace();

		ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}
}
