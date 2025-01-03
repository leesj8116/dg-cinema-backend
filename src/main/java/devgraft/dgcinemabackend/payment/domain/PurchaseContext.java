package devgraft.dgcinemabackend.payment.domain;

public record PurchaseContext(
	Long reservationId,
	Integer amount
) {
	public PurchaseContext {

	}
}
