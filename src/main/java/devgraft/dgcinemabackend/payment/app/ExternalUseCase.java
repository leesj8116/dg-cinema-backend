package devgraft.dgcinemabackend.payment.app;

public interface ExternalUseCase {
	Boolean purchase();

	Boolean refund();
}
