package devgraft.dgcinemabackend.cinema.domain;

import java.util.List;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Table(name = "cinema")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cinema extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cinemaId;

	@Column(nullable = false, length = 50)
	private String name;        // 영화관 이름

	@Column(nullable = false, length = 200)
	private String location;    // 영화관 주소

	@OneToMany
	@JoinColumn(name = "cinema_id")
	private List<ScreenRoom> screenRoomList;
}
