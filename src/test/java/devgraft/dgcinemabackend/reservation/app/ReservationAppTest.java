package devgraft.dgcinemabackend.reservation.app;

import static devgraft.dgcinemabackend.reservation.ReservationFixture.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.GetMyReservationRequest;
import devgraft.dgcinemabackend.reservation.domain.PurchaseContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationErrorCode;
import devgraft.dgcinemabackend.reservation.domain.ReservationException;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;

@ExtendWith(MockitoExtension.class)
class ReservationAppTest {
	@InjectMocks
	private ReservationApp reservationApp;

	@Mock
	private DgUserFinder dgUserFinder;

	@Mock
	private RunningTimeFinder runningTimeFinder;

	@Mock
	private ReservationRepository reservationRepository;

	@BeforeEach
	void setUp() {
		Mockito.lenient()
			.when(dgUserFinder.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(anDgUser().build()));
		Mockito.lenient()
			.when(runningTimeFinder.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(anRunningTime().build()));
		Mockito.lenient()
			.when(reservationRepository.findSeatNoByRunningTimeAndStatusIn(Mockito.any(), Mockito.anySet()))
			.thenReturn(List.of());
	}

	@Test
	@DisplayName("예약 좌석 현황 조회시 상영시간 정보가 없을 경우 에러를 반환한다")
	void seat_check_should_throw_exception_when_running_time_not_found() {
		Mockito.when(runningTimeFinder.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationApp.seatCheck(1L));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.RUNNING_TIME_NOT_FOUND);
	}

	@Test
	@DisplayName("예약 등록시 사용자 정보가 없을 경우 에러를 반환한다")
	void register_should_throw_exception_when_user_not_found() {
		final ReservationContext givenContext = new ReservationContext(1L, 1L, "A1");
		Mockito.when(dgUserFinder.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationApp.register(givenContext));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.USER_NOT_FOUND);
	}

	@Test
	@DisplayName("예약 등록시 상영 시간 정보가 없을 경우 에러를 반환한다")
	void register_should_throw_exception_when_running_time_not_found() {
		final ReservationContext givenContext = new ReservationContext(1L, 1L, "A1");
		Mockito.when(runningTimeFinder.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationApp.register(givenContext));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.RUNNING_TIME_NOT_FOUND);
	}

	@Test
	@DisplayName("예약 등록시 이미 예약된 좌석일 경우 에러를 반환한다")
	void register_should_throw_exception_when_seat_already_exists() {
		final ReservationContext givenContext = new ReservationContext(1L, 1L, "A1");
		Mockito.when(reservationRepository.findSeatNoByRunningTimeAndStatusIn(Mockito.any(), Mockito.anySet())).thenReturn(List.of("A1", "A2"));

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationApp.register(givenContext));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.ALREADY_SEAT_NO_HAS_RESERVED);
	}

	@Test
	@DisplayName("나의 예약 확인시, 사용자가 존재하지 않을 경우 에러를 반환한다")
	void get_user_reservation_should_throw_exception_when_user_not_found() {
		GetMyReservationRequest givenRequest = new GetMyReservationRequest(1L);
		Mockito.when(dgUserFinder.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationApp.getUserReservations(givenRequest));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.USER_NOT_FOUND);
	}

	@Test
	@DisplayName("예약 만료 확인 호출시, 유효시간(생성으로 부터 5분)이 지나면 에러를 반환한다")
	void reservation_is_available_should_throw_exception_when_reservation_created_date_has_left_5_minutes() {
		Mockito.when(reservationRepository.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(
				anReservation().createdDate(LocalDateTime.now().minusMinutes(5L)).build()
			));

		final PurchaseContext purchaseContext = new PurchaseContext(1L, 10000);

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationApp.purchase(purchaseContext)
		);

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.ALREADY_RESERVATION_HAS_NOT_AVAILABLE);

	}
}