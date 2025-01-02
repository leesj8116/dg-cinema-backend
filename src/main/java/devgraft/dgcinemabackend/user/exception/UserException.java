package devgraft.dgcinemabackend.user.exception;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
	private final ErrorCode errorCode;

	public UserException(final String message) {
		this(message, CommonErrorCode.INTERNAL_SERVER_ERROR);
	}

	public UserException(final ErrorCode errorCode) {
		this(errorCode.getMessage(), errorCode);
	}

	public UserException(final String message, final ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
