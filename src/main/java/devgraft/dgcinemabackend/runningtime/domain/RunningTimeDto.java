package devgraft.dgcinemabackend.runningtime.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunningTimeDto {
	private Long runningTimeId;
	@NotNull(message = "상영 시간을 입력해주세요")
	private LocalDateTime startTime;
	@NotNull(message = "영화 아이디를 입력해주세요")
	private Long movieId;
	@NotNull(message = "극장 번호를 입력해주세요")
	private Long cinemaId;
	@NotNull(message = "상영관 번호를 입력해주세요")
	private Long screenNumber;
	@Builder.Default
	private LocalDateTime createdDate = LocalDateTime.now();
	@Builder.Default
	private LocalDateTime lastModifiedDate = LocalDateTime.now();

	public RunningTimeDto(RunningTime entity) {
		this.runningTimeId = entity.getRunningTimeId();
		this.startTime = entity.getStartTime();
		this.movieId = entity.getMovie().getMovieId();
		this.cinemaId = entity.getScreenRoom().getCinemaId();
		this.screenNumber = entity.getScreenRoom().getScreenNumber();
	}
}
