package devgraft.dgcinemabackend.payment.app;

import devgraft.dgcinemabackend.payment.domain.PaymentResult;
import devgraft.dgcinemabackend.payment.domain.PurchaseContext;

public interface PaymentUseCase {
	PaymentResult purchase(final PurchaseContext purchaseContext, final Boolean result, final String message);

	void reservationIsAvailable(final Long reservationId);
}
