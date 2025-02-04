package devgraft.dgcinemabackend.payment.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import devgraft.dgcinemabackend.common.exception.CommonExceptionHandler;
import devgraft.dgcinemabackend.payment.domain.PaymentException;

@RestControllerAdvice
class PaymentExceptionHandler extends CommonExceptionHandler {

	@ExceptionHandler({PaymentException.class})
	public ResponseEntity<Object> handlePaymentException(final PaymentException e) {
		e.printStackTrace();

		return handleExceptionInternal(e.getErrorCode(), e.getMessage());
	}
}
