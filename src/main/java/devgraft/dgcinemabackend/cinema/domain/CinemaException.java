package devgraft.dgcinemabackend.cinema.domain;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CinemaException extends RuntimeException {
	private final ErrorCode errorCode;

	public CinemaException(ErrorCode errorCode) {
		this(errorCode.getMessage(), errorCode);
	}

	public CinemaException(String message) {
		this(message, CommonErrorCode.INTERNAL_SERVER_ERROR);
	}

	public CinemaException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
