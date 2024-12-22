package devgraft.dgcinemabackend.screenroom.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ScreenRoomPK implements Serializable {
	private Long cinemaId;
	private Long screenNumber;
}
