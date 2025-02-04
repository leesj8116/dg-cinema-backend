package devgraft.dgcinemabackend.payment.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.payment.app.PaymentUseCase;
import devgraft.dgcinemabackend.payment.domain.PaymentResult;
import devgraft.dgcinemabackend.payment.domain.PurchaseContext;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentApi {
	private final PaymentUseCase paymentUseCase;

	/**
	 * 예약 건에 대해 결제 처리한다.
	 * @param purchaseContext
	 * @return
	 */
	@PostMapping("/payment/purchase")
	public PaymentResult register(@RequestBody PurchaseContext purchaseContext) {
		return paymentUseCase.purchase(purchaseContext);
	}
}
