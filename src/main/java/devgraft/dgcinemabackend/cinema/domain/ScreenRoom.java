package devgraft.dgcinemabackend.screenroom.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "screen_room")
@NoArgsConstructor
@IdClass(ScreenRoomPK.class)
@Entity
public class ScreenRoom extends BaseEntity {
	@Id
	private Long cinemaId;
	@Id
	private Long screenNumber;

	public ScreenRoom(Long cinemaId, Long screenNumber) {
		this.cinemaId = cinemaId;
		this.screenNumber = screenNumber;
	}
}
