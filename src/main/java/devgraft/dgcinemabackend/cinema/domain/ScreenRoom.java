package devgraft.dgcinemabackend.cinema.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Table(name = "screen_room")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScreenRoomPK.class)
@Entity
public class ScreenRoom extends BaseEntity {
	@Id
	@Column(name = "cinema_id")
	private Long cinemaId;
	@Id
	private Long screenNumber;
}
