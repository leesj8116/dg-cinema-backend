package devgraft.dgcinemabackend.reservation.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.reservation.domain.Payment;

interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
	Payment save(final Payment payment);

	List<Payment> findByReservationId(final Long reservationId);
}
