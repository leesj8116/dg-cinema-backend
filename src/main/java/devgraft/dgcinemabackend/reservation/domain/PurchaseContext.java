package devgraft.dgcinemabackend.reservation.domain;

public record PurchaseContext(
	Long reservationId,
	Integer amount
) {
	public PurchaseContext {

	}
}
