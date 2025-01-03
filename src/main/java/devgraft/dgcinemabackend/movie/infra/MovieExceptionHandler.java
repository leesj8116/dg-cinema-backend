package devgraft.dgcinemabackend.movie.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.movie.domain.MovieException;

@RestControllerAdvice
class MovieExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(MovieException.class)
	public ResponseEntity<Object> handleMovieException(final MovieException e) {
		e.printStackTrace();

		return handleExceptionInternal(e.getErrorCode(), e.getMessage());
	}
}
