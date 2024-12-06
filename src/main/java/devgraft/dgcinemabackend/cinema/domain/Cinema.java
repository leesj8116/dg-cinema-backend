package devgraft.dgcinemabackend.cinema.domain;

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
@Table(name = "cinema")
@NoArgsConstructor
@Entity
public class Cinema extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cinemaId;

	@Column(nullable = false, length = 50)
	private String name;        // 영화관 이름

	@Column(nullable = false, length = 200)
	private String location;    // 영화관 주소

	public Cinema(Long cinemaId, String name, String location) {
		super();
		this.cinemaId = cinemaId;
		this.name = name;
		this.location = location;
	}

	public Cinema(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}
}
