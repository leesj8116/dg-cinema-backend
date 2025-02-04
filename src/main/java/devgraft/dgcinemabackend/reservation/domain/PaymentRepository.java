package devgraft.dgcinemabackend.reservation.domain;

import java.util.Optional;

public interface PaymentRepository {
	Payment save(final Payment payment);

	Optional<Payment> findByReservationId(final Long reservationId);
}
