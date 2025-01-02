package devgraft.dgcinemabackend.movie.exception;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MovieException extends RuntimeException {
	private final ErrorCode errorCode;

	public MovieException(final ErrorCode errorCode) {
		this(errorCode.getMessage(), errorCode);
	}

	public MovieException(final String message) {
		this(message, CommonErrorCode.INTERNAL_SERVER_ERROR);
	}

	public MovieException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
