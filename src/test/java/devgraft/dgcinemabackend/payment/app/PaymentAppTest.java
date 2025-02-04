package devgraft.dgcinemabackend.payment.app;

import static devgraft.dgcinemabackend.payment.PaymentFixture.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import devgraft.dgcinemabackend.payment.domain.PaymentErrorCode;
import devgraft.dgcinemabackend.payment.domain.PaymentException;
import devgraft.dgcinemabackend.payment.domain.PaymentRepository;
import devgraft.dgcinemabackend.payment.domain.PurchaseContext;
import devgraft.dgcinemabackend.payment.domain.ReservationFinder;
import devgraft.dgcinemabackend.payment.domain.ReservationRegister;

@ExtendWith(MockitoExtension.class)
class PaymentAppTest {
	@InjectMocks
	private PaymentApp paymentApp;

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private ReservationFinder reservationFinder;

	@Mock
	private ReservationRegister reservationRegister;

	@Test
	@DisplayName("예약 만료 확인 호출시, 유효시간(생성으로 부터 5분)이 지나면 에러를 반환한다")
	void reservation_is_available_should_throw_exception_when_reservation_created_date_has_left_5_minutes() {
		Mockito.when(reservationFinder.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(
				anReservation().createdDate(LocalDateTime.now().minusMinutes(5L)).build()
			));

		final PurchaseContext purchaseContext = new PurchaseContext(1L, 10000);

		final PaymentException exception = Assertions.catchThrowableOfType(
			PaymentException.class, () -> paymentApp.purchase(purchaseContext)
		);

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(PaymentErrorCode.RESERVATION_IS_EXPIRED);

	}
}