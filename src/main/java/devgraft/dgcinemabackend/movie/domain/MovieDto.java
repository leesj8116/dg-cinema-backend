package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;
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
public class MovieDto {
	private Long movieId;
	@NotBlank(message = "영화 제목은 필수 입력 값입니다.")
	private String title;
	private String director;
	private LocalDate releaseDate;
	@Builder.Default
	private LocalDateTime createdDate = LocalDateTime.now();
	@Builder.Default
	private LocalDateTime lastModifiedDate = LocalDateTime.now();

	public MovieDto(Movie entity) {
		this.movieId = entity.getMovieId();
		this.title = entity.getTitle();
		this.director = entity.getDirector();
		this.releaseDate = entity.getReleaseDate();
		this.createdDate = entity.getCreatedDate();
		this.lastModifiedDate = entity.getLastModifiedDate();
	}
}
