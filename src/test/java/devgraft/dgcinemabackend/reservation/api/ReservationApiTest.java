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
import devgraft.dgcinemabackend.reservation.domain.CinemaFinder;
import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.reservation.domain.Reservation;
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationExceptionMessage;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.ReservationResult;
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
	private CinemaFinder cinemaFinder;

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

	//////////////////////// 예약 좌석 조회 ////////////////////////

	// @FIXME: 잘못된 테스트코드
	@Test
	@DisplayName("예약 좌석 조회시 상영 시간은 존재해야 한다")
	void seat_check_should_throw_exception_when_running_time_not_found() {
		// given
		Long runningTimeId = 9999L;
		Mockito.when(runningTimeFinder.findById(runningTimeId)).thenReturn(Optional.empty());
		// when
		final IllegalArgumentException exception = Assertions.catchThrowableOfType(
			() -> reservationApp.seatCheck(runningTimeId),
			IllegalArgumentException.class);

		// then
		ArgumentCaptor<Long> runningTimeCaptor = ArgumentCaptor.forClass(Long.class);
		Mockito.verify(runningTimeFinder, Mockito.times(2)).findById(runningTimeCaptor.capture());

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getMessage())
			.isEqualTo(ReservationExceptionMessage.RUNNING_TIME_NOT_FOUND.getMessage());
		Assertions.assertThat(runningTimeCaptor.getValue()).isEqualTo(runningTimeId);
	}

	//////////////////////// 예약 검사 ////////////////////////

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
		Assertions.assertThat(exception.getMessage())
			.isEqualTo(ReservationExceptionMessage.USER_NOT_FOUND.getMessage());
		Assertions.assertThat(userIdCaptor.getValue()).isEqualTo(givenContext.userId());
	}

	@Test
	@DisplayName("예약 등록시 상영 시간 정보가 존재해야 한다.")
	void register_should_throw_exception_when_runningtime_not_found() {
		// given
		final Long dummyReservationId = 999L;
		Reservation reservation = anReservation().runningTime(anRunningTime().runningTimeId(dummyReservationId).build())
			.build();
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
		Assertions.assertThat(exception.getMessage())
			.isEqualTo(ReservationExceptionMessage.RUNNING_TIME_NOT_FOUND.getMessage());
		Assertions.assertThat(runningTimeCaptor.getValue()).isEqualTo(dummyReservationId);
	}

	@Test
	@DisplayName("예약을 정상적으로 등록해야 한다.")
	void register_should_save_reservation() {
		// given
		Reservation reservation = anReservation().build();

		final ReservationContext reservationContext = new ReservationContext(
			reservation.getUser().getUserId(),
			reservation.getRunningTime().getRunningTimeId(),
			reservation.getSeatNo());

		final ArgumentCaptor<Long> runningTimeCaptor = ArgumentCaptor.forClass(Long.class);
		final ArgumentCaptor<Long> userCaptor = ArgumentCaptor.forClass(Long.class);
		final ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

		Mockito.when(dgUserFinder.findById(Mockito.anyLong())).thenReturn(Optional.of(reservation.getUser()));
		Mockito.when(cinemaFinder.findById(Mockito.anyLong())).thenReturn(Optional.of(anCinema().build()));
		Mockito.when(runningTimeFinder.findById(Mockito.anyLong())).thenReturn(Optional.of(anRunningTime().build()));
		Mockito.when(reservationRepository.save(Mockito.any())).thenReturn(reservation);

		// when
		ReservationResult result = reservationApp.register(reservationContext);
		Mockito.verify(dgUserFinder, Mockito.times(1)).findById(userCaptor.capture());
		Mockito.verify(runningTimeFinder, Mockito.times(1)).findById(runningTimeCaptor.capture());
		Mockito.verify(reservationRepository, Mockito.times(1)).save(reservationCaptor.capture());

		// @TODO: [재검토] 정상 동작 API 테스트는 어디까지가 좋은가?
		// then
		Assertions.assertThat(result.reservationId())
			.isEqualTo(reservation.getReservationId());                     // 아이디
		Assertions.assertThat(userCaptor.getValue())
			.isEqualTo(reservation.getUser().getUserId());                  // 유저
		Assertions.assertThat(runningTimeCaptor.getValue())
			.isEqualTo(reservation.getRunningTime().getRunningTimeId());    // 상영시간
		Assertions.assertThat(reservationCaptor.getValue().getSeatNo())
			.isEqualTo(reservation.getSeatNo());                            // 좌석
	}
}