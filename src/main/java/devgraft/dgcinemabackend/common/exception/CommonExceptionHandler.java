package devgraft.dgcinemabackend.common.exception;

import org.springframework.http.ResponseEntity;

/**
 * https://velog.io/@woosim34/RestControllerAdvice를-이용한-예외처리
 * 을 참조하여 ExceptionHandler 구현
 */
public abstract class CommonExceptionHandler {
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
