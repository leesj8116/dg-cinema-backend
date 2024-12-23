package devgraft.dgcinemabackend.reservation.api;

import static devgraft.dgcinemabackend.reservation.ReservationFixture.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import devgraft.dgcinemabackend.reservation.app.ReservationApp;
import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationExceptionMessage;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;

@ExtendWith(MockitoExtension.class)
class ReservationApiTest {
	@InjectMocks
	private ReservationApp reservationApp;

	@Mock
	private DgUserFinder dgUserFinder;

	@Mock
	private RunningTimeFinder runningTimeFinder;

	@Mock
	private ReservationRepository reservationRepository;

	// @Captor
	// private ArgumentCaptor<Long> runningTimeCaptor; // 왜 에러 나지..

	@BeforeEach
	void setUp() {
		// lenient는 호출을 강제하지 않음
		Mockito.lenient()
			.when(dgUserFinder.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(anDgUser().build()));
	}

	// 유저가 존재하는지 검사, 만약 없을 경우 예외 처리
	@Test
	@DisplayName("예약 등록시 유저는 존재해야 한다")
	void register_should_throw_exception_when_user_not_found() {
		// given
		Reservation reservation = anReservation().reservationId(9999L).build();
		final ReservationContext givenContext = new ReservationContext(
			reservation.getReservationId(),
			reservation.getRunningTime().getRunningTimeId(),
			reservation.getSeatNo());

		// Mockito.when 는 호출을 강제함
		Mockito.when(dgUserFinder.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		// when
		final IllegalArgumentException exception = Assertions.catchThrowableOfType(
			() -> reservationApp.register(givenContext),
			IllegalArgumentException.class);

		ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
		// verify(dgUserRepository, Mockito.times(1)) : findById를 몇 번 콜했는지 체크 하는 횟수.
		// 못해도 dgUserRepository를 종속받아서 findById를 1번 호출해야함
		Mockito.verify(dgUserFinder, Mockito.times(1)).findById(userIdCaptor.capture());

		// then
		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getMessage()).isEqualTo(ReservationExceptionMessage.USER_NOT_FOUND.getMessage());
		Assertions.assertThat(userIdCaptor.getValue()).isEqualTo(givenContext.userId());
	}

	@Test
	@DisplayName("예약 등록시 상영 시간 정보가 존재해야 한다.")
	void register_should_throw_exception_when_runningtime_not_found() {
		// given
		final Long dummyReservationId = 999L;
		Reservation reservation = anReservation().runningTime(anRunningTime().runningTimeId(dummyReservationId).build()).build();
		final ReservationContext reservationContext = new ReservationContext(
			reservation.getReservationId(),
			reservation.getRunningTime().getRunningTimeId(),
			reservation.getSeatNo());

		// when
		final IllegalArgumentException exception = Assertions.catchThrowableOfType(
			() -> reservationApp.register(reservationContext),
			IllegalArgumentException.class);

		ArgumentCaptor<Long> runningTimeCaptor = ArgumentCaptor.forClass(Long.class);
		// 꼭 위의 방법대로 할 필요 없이, @Captor 어노테이션으로 할 수 있음
		Mockito.verify(runningTimeFinder, Mockito.times(1)).findById(runningTimeCaptor.capture());

		// then
		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getMessage()).isEqualTo(ReservationExceptionMessage.RUNNING_TIME_NOT_FOUND.getMessage());
		Assertions.assertThat(runningTimeCaptor.getValue()).isEqualTo(dummyReservationId);
	}

	@Test
	@DisplayName("예약을 정상적으로 등록해야 한다.")
	void register_should_save_reservation() {
		// given
		Reservation reservation = anReservation().runningTime(anRunningTime().build()).build();

		final ReservationContext reservationContext = new ReservationContext(
			reservation.getReservationId(),
			reservation.getRunningTime().getRunningTimeId(),
			reservation.getSeatNo());

		final ArgumentCaptor<Long> runningTimeCaptor = ArgumentCaptor.forClass(Long.class);
		final ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

		Mockito.when(dgUserFinder.findById(Mockito.anyLong())).thenReturn(Optional.of(anDgUser().build()));
		// Mockito.when(runningTimeFinder.findById(Mockito.anyLong())).thenReturn(Optional.of(anRunningTime().build()));

		// when
		Mockito.verify(runningTimeFinder, Mockito.times(1)).findById(runningTimeCaptor.capture());
		reservationApp.register(reservationContext);

		// then
		Mockito.verify(reservationRepository, Mockito.times(1)).save(reservationCaptor.capture());

		Assertions.assertThat(reservationCaptor.getValue()).isNotNull();
		Assertions.assertThat(runningTimeCaptor.getValue()).isEqualTo(reservation.getReservationId());
	}
}