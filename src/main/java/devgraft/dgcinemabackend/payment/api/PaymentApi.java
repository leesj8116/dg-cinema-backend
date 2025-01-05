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

		// 이 구조대로라면.. 외부 API 호출 전에
		// 예약이 생성된지 5분 이내인지 확인하는 과정이 필요하다.
		// 결제 모듈에 요청하고 나서 예약이 유효하지 않으면 문제니까...
		// 그러면 예약 Api에 한 번 더 요청해야하나?
		// 화면 갱신이 안돼서, 화면에는 계속 예약 중으로 보일 수도 있는데..?
		paymentUseCase.reservationIsAvailable(purchaseContext.reservationId());

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
