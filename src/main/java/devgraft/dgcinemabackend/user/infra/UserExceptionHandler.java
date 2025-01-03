package devgraft.dgcinemabackend.user.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.user.domain.UserException;

@RestControllerAdvice
public class UserExceptionHandler extends CommonExceptionHandler {
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Object> handleUserException(UserException e) {
		e.printStackTrace();

		return handleExceptionInternal(e.getErrorCode(), e.getMessage());
	}
}
