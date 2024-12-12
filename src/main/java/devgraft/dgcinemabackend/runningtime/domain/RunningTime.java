package devgraft.dgcinemabackend.runningtime.domain;

import java.time.LocalDateTime;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "running_time")
@NoArgsConstructor
@Entity
public class RunningTime extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long runningTimeId;

	@Column(nullable = false)
	private LocalDateTime startTime;            // 상영시작 시간

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;                        // 영화

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "cinema_id"),
		@JoinColumn(name = "screen_number")
	})
	private ScreenRoom screenRoom;              // 상영관
}
