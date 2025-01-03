package devgraft.dgcinemabackend.payment.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.payment.app.ExternalUseCase;
import devgraft.dgcinemabackend.payment.app.PaymentUseCase;
import devgraft.dgcinemabackend.payment.domain.PaymentException;
import devgraft.dgcinemabackend.payment.domain.PaymentResult;
import devgraft.dgcinemabackend.payment.domain.PurchaseContext;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentApi {
	private final PaymentUseCase paymentUseCase;
	private final ExternalUseCase externalUseCase;  // 외부 결제 API (벤더사? 더미)

	/**
	 * 예약 건에 대해 결제 처리한다.
	 * @param purchaseContext
	 * @return
	 */
	@PostMapping("/payment/purchase")
	public PaymentResult register(@RequestBody PurchaseContext purchaseContext) {
		Boolean result = null;
		String message = null;
		try {
			result = externalUseCase.purchase();

			if (!result) {
				throw new PaymentException("외부 API 결제 실패");
			}
		} catch (PaymentException e) {
			e.printStackTrace();
			result = Boolean.FALSE;
			message = e.getMessage();
		}

		return paymentUseCase.purchase(purchaseContext, result, message);
	}
}
