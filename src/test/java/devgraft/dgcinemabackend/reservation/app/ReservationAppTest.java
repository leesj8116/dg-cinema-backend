package devgraft.dgcinemabackend.reservation.app;

import static devgraft.dgcinemabackend.reservation.ReservationFixture.*;

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
import devgraft.dgcinemabackend.reservation.domain.ReservationContext;
import devgraft.dgcinemabackend.reservation.domain.ReservationErrorCode;
import devgraft.dgcinemabackend.reservation.domain.ReservationException;
import devgraft.dgcinemabackend.reservation.domain.ReservationRepository;
import devgraft.dgcinemabackend.reservation.domain.RunningTimeFinder;

@ExtendWith(MockitoExtension.class)
class ReservationAppTest {
	@InjectMocks
	private ReservationUseCase reservationUseCase;

	@Mock
	private DgUserFinder dgUserFinder;

	@Mock
	private RunningTimeFinder runningTimeFinder;

	@Mock
	private ReservationRepository reservationRepository;

	@BeforeEach
	void setUp() {
		reservationUseCase = new ReservationApp(reservationRepository, runningTimeFinder, dgUserFinder);

		Mockito.lenient()
			.when(dgUserFinder.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(anDgUser().build()));
		Mockito.lenient()
			.when(runningTimeFinder.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(anRunningTime().build()));
		Mockito.lenient()
			.when(reservationRepository.findSeatNoByRunningTime(Mockito.any()))
			.thenReturn(List.of());
	}

	@Test
	@DisplayName("예약 좌석 현황 조회시 상영시간 정보가 없을 경우 에러를 반환한다")
	void seat_check_should_throw_exception_when_running_time_not_found() {
		Mockito.when(runningTimeFinder.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationUseCase.seatCheck(1L));

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
			ReservationException.class, () -> reservationUseCase.register(givenContext));

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
			ReservationException.class, () -> reservationUseCase.register(givenContext));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.RUNNING_TIME_NOT_FOUND);
	}

	@Test
	@DisplayName("예약 등록시 이미 예약된 좌석일 경우 에러를 반환한다")
	void register_should_throw_exception_when_seat_already_exists() {
		final ReservationContext givenContext = new ReservationContext(1L, 1L, "A1");
		Mockito.when(reservationRepository.findSeatNoByRunningTime(Mockito.any())).thenReturn(List.of("A1", "A2"));

		final ReservationException exception = Assertions.catchThrowableOfType(
			ReservationException.class, () -> reservationUseCase.register(givenContext));

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
			ReservationException.class, () -> reservationUseCase.getUserReservations(givenRequest));

		Assertions.assertThat(exception).isNotNull();
		Assertions.assertThat(exception.getErrorCode())
			.isEqualTo(ReservationErrorCode.USER_NOT_FOUND);
	}

}