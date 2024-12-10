package devgraft.dgcinemabackend.screenroom.domain;

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
public class ScreenRoomDto {
	private Long cinemaId;
	@NotNull(message = "상영관 번호를 필수로 입력해야합니다.")
	private Long screenNumber;
	@Builder.Default
	private LocalDateTime createdDate = LocalDateTime.now();
	@Builder.Default
	private LocalDateTime lastModifiedDate = LocalDateTime.now();

	public ScreenRoomDto(ScreenRoom screenRoom) {
		this.cinemaId = screenRoom.getCinemaId();
		this.screenNumber = screenRoom.getScreenNumber();
		this.createdDate = screenRoom.getCreatedDate();
		this.lastModifiedDate = screenRoom.getLastModifiedDate();
	}
}
