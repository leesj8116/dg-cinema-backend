package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;

import org.springframework.util.Assert;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "movie")
@NoArgsConstructor
@Entity
public class Movie extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;              // 아이디

	@Column(nullable = false, length = 200)
	private String title;                // 영화 제목

	@Column(nullable = false, length = 50)
	private String director;            // 감독

	@Column(nullable = false)
	private LocalDate releaseDate;  // 개봉일

	public Movie(final Long movieId, final String title, final String director, final LocalDate releaseDate) {
		this.movieId = movieId;
		setTitle(title);
		setDirector(director);
		this.releaseDate = releaseDate;
	}

	public Movie(final String title, final String director, final LocalDate releaseDate) {
		this(null, title, director, releaseDate);
	}

	private void setTitle(final String title) {
		Assert.hasText(title, "영화 제목은 필수 값입니다");
		Assert.isTrue(title.length() < 200, "영화 제목의 길이는 200을 넘지 않아야 합니다.");
		this.title = title;
	}

	private void setDirector(final String director) {
		Assert.isTrue(director.length() < 50, "감독의 이름은 50자를 넘지 않아야 합니다.");
	}
}
