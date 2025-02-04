package devgraft.dgcinemabackend.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * https://velog.io/@woosim34/RestControllerAdvice를-이용한-예외처리
 * 을 참조하여 ExceptionHandler 구현
 */
@RestControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Object> handleException(Exception e) {
		e.printStackTrace();

		ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
		return handleExceptionInternal(errorCode);
	}

	protected ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(makeErrorResponse(errorCode));
	}

	protected ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode, final String message) {
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(makeErrorResponse(errorCode, message));
	}

	protected ErrorResponse makeErrorResponse(final ErrorCode errorCode) {
		return ErrorResponse.of(errorCode.name(), errorCode.getMessage());
	}

	protected ErrorResponse makeErrorResponse(final ErrorCode errorCode, final String message) {
		return ErrorResponse.of(errorCode.name(), message);
	}
}
