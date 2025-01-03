package devgraft.dgcinemabackend.payment.domain;

public record PaymentResult(
	Long paymentId,
	Integer amount,
	Boolean result,
	String message
) {
	public static PaymentResult of(Long paymentId, Integer amount, Boolean result, String message) {
		return new PaymentResult(paymentId, amount, result, message);
	}

}
