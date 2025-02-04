package devgraft.dgcinemabackend.payment.domain;

public record PaymentResult(
	Long paymentId,
	Integer amount,
	Boolean result
) {
	public static PaymentResult of(Long paymentId, Integer amount, Boolean result) {
		return new PaymentResult(paymentId, amount, result);
	}

}
