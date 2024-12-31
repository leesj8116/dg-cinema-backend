package devgraft.dgcinemabackend.reservation.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.reservation.domain.Payment;

interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
	Payment save(final Payment payment);
}
