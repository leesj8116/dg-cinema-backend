package devgraft.dgcinemabackend.cinema.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDto {
	private Long cinemaId;
	@NotBlank(message = "영화관 이름은 필수 입력 값입니다.")
	private String name;
	private String location;
	@Builder.Default
	private LocalDateTime createdDate = LocalDateTime.now();
	@Builder.Default
	private LocalDateTime lastModifiedDate = LocalDateTime.now();

	public CinemaDto(Cinema entity) {
		this.cinemaId = entity.getCinemaId();
		this.name = entity.getName();
		this.location = entity.getLocation();
		this.createdDate = entity.getCreatedDate();
		this.lastModifiedDate = entity.getLastModifiedDate();
	}
}
