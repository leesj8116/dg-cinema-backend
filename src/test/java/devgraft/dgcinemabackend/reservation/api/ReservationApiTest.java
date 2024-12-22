package devgraft.dgcinemabackend.reservation.api;

import static devgraft.dgcinemabackend.reservation.ReservationFixture.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import devgraft.dgcinemabackend.reservation.app.ReservationApp;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;

@ExtendWith(MockitoExtension.class)
class ReservationApiTest {
	@InjectMocks
	private ReservationApp reservationApp;

	@Mock
	private DgUserRepository dgUserRepository;

	@Mock
	private RunningTimeRepository runningTimeRepository;

	@Mock
	private ReservationRepository reservationRepository;

	@Captor
	private ArgumentCaptor<Long> runningTimeCaptor;

	@BeforeEach
	void setUp() {
		// lenient는 호출을 강제하지 않음
		Mockito.lenient()
			.when(dgUserRepository.findById(Mockito.anyLong()))
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
			reservation.getSeetNo());

		// Mockito.when 는 호출을 강제함
		Mockito.when(dgUserRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		// when
		final IllegalArgumentException exception = Assertions.catchThrowableOfType(
			() -> reservationApp.register(givenContext),
			IllegalArgumentException.class);

		ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
		// verify(dgUserRepository, Mockito.times(1)) : findById를 몇 번 콜했는지 체크 하는 횟수.
		// 못해도 dgUserRepository를 종속받아서 findById를 1번 호출해야함
		Mockito.verify(dgUserRepository, Mockito.times(1)).findById(userIdCaptor.capture());

		// then
		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getMessage()).isEqualTo("User does not exist");
		Assertions.assertThat(userIdCaptor.getValue()).isEqualTo(givenContext.userId());
	}

	@Test
	@DisplayName("예약 등록시 상영 시간 정보가 존재해야 한다.")
	void register_should_throw_exception_when_runningtime_not_found() {
		// given
		Reservation reservation = anReservation().runningTime(anRunningTime().runningTimeId(9999L).build()).build();
		final ReservationContext reservationContext = new ReservationContext(
			reservation.getReservationId(),
			reservation.getRunningTime().getRunningTimeId(),
			reservation.getSeetNo());

		// when
		final IllegalArgumentException exception = Assertions.catchThrowableOfType(
			() -> reservationApp.register(reservationContext),
			IllegalArgumentException.class);

		// ArgumentCaptor<Long> runningTimeCaptor = ArgumentCaptor.forClass(Long.class);
		// 꼭 위의 방법대로 할 필요 없이, @Captor 어노테이션으로 할 수 있음
		Mockito.verify(runningTimeRepository, Mockito.times(1)).findById(runningTimeCaptor.capture());

		// then
		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getMessage()).isEqualTo("RunningTime does not exist");
		Assertions.assertThat(runningTimeCaptor.getValue()).isEqualTo(123L);
	}

	@Test
	@DisplayName("예약 등록시 상영 시간 정보가 존재해야 한다.")
	void register_should_save_reservation() {
		// given
		Reservation reservation = anReservation().runningTime(anRunningTime().build()).build();

		final ReservationContext reservationContext = new ReservationContext(
			reservation.getReservationId(),
			reservation.getRunningTime().getRunningTimeId(),
			reservation.getSeetNo());

		// when
		reservationApp.register(reservationContext);

		// then
		final ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);
		Mockito.verify(reservationRepository, Mockito.times(1)).save(reservationCaptor.capture());

		Assertions.assertThat(reservationCaptor.getValue()).isNotNull();
		// Assertions.assertThat(exception.getMessage()).isEqualTo("RunningTime does not exist");
		// Assertions.assertThat(runningTimeCaptor.getValue()).isEqualTo(123L);
	}
}