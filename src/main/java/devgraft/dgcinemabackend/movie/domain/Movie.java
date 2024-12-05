package devgraft.dgcinemabackend.movie.domain;

import java.time.LocalDate;

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

	public Movie(Long movieId, String title, String director, LocalDate releaseDate) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.director = director;
		this.releaseDate = releaseDate;
	}

	public Movie(String title, String director, LocalDate releaseDate) {
		super();
		this.title = title;
		this.director = director;
		this.releaseDate = releaseDate;
	}
}
