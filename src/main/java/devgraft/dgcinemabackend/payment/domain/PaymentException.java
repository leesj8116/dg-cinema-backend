package devgraft.dgcinemabackend.payment.domain;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {
	private final ErrorCode errorCode;

	public PaymentException(final ErrorCode errorCode) {
		this(errorCode.getMessage(), errorCode);
	}

	public PaymentException(final String message) {
		this(message, CommonErrorCode.INTERNAL_SERVER_ERROR);
	}

	public PaymentException(final String message, final ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
