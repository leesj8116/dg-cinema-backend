package devgraft.dgcinemabackend.payment.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.payment.domain.Payment;

interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
