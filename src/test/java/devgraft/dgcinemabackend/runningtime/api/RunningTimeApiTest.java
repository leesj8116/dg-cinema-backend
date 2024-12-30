package devgraft.dgcinemabackend.runningtime.api;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import devgraft.dgcinemabackend.runningtime.RunningTimeFixture;
import devgraft.dgcinemabackend.runningtime.app.RunningTimeApp;
import devgraft.dgcinemabackend.runningtime.domain.MovieFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;

@ExtendWith(MockitoExtension.class)
class RunningTimeApiTest {
	@InjectMocks
	private RunningTimeApp runningTimeApp;

	@Mock
	private MovieFinder movieFinder;

	@Mock
	private RunningTimeRepository runningTimeRepository;

	@Test
	@DisplayName("상영 시간 검색시, 이름으로 영화를 찾을 수 없다면 아직 상영 전인 모든 상영시간을 반환한다")
	void get_running_times_should_return_all_list_when_no_movies_found() {
		// given
		Mockito.when(movieFinder.findMoviesByMovieTitle(Mockito.anyString())).thenReturn(List.of());
		Mockito.when(
				runningTimeRepository.findAllByStartTimeGreaterThanEqualAndMovieIn(Mockito.any(), Mockito.anyList()))
			.thenReturn(List.of(
				RunningTime.builder()
					.runningTimeId(1L)
					.startTime(LocalDateTime.now())
					.movie(RunningTimeFixture.anMovie().title("hello world!").build())
					.build(),
				RunningTime.builder()
					.runningTimeId(2L)
					.startTime(LocalDateTime.now())
					.movie(RunningTimeFixture.anMovie().title("Metal Slug X").build())
					.build()
			));

		final ArgumentCaptor<String> titleCaptor = ArgumentCaptor.forClass(String.class);

		// when
		final String inputTitle = "title";
		List<RunningTime> runningTimes = runningTimeApp.getRunningTimesByMovieTitle(inputTitle);
		Mockito.verify(movieFinder, Mockito.times(1)).findMoviesByMovieTitle(titleCaptor.capture());

		// then
		Assertions.assertThat(runningTimes).hasSize(2);
		// 검색 조건으로 입력한 영화 제목을 포함하지 않아야 모든 리스트를 반환한다.
		Assertions.assertThat(titleCaptor.getValue()).isEqualTo(inputTitle);
		Assertions.assertThat(runningTimes.stream()
			.map(runningTime -> runningTime.getMovie().getTitle())
			.anyMatch(a -> a.contains(inputTitle))).isFalse();

	}
}