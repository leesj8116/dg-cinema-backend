package devgraft.dgcinemabackend.screenroom.domain;

public interface ScreenRoomRepository {
	ScreenRoom register(CreateScreenRoomRequest createScreenRoomRequest);
}
