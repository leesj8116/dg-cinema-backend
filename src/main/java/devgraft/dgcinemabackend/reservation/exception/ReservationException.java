package devgraft.dgcinemabackend.reservation.exception;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReservationException extends RuntimeException {
	private final ErrorCode errorCode;

	public ReservationException(final String message) {
		this(message, CommonErrorCode.INTERNAL_SERVER_ERROR);
	}

	public ReservationException(final String message, final ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
