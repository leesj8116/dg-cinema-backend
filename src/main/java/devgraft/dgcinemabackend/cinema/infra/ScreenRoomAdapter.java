package devgraft.dgcinemabackend.screenroom.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.screenroom.domain.CreateScreenRoomRequest;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoomRepository;

@Component
public class ScreenRoomAdapter implements ScreenRoomRepository {
	private final ScreenRoomJpaRepository screenRoomJpaRepository;

	public ScreenRoomAdapter(ScreenRoomJpaRepository screenRoomJpaRepository) {
		this.screenRoomJpaRepository = screenRoomJpaRepository;
	}

	@Override
	public ScreenRoom register(CreateScreenRoomRequest createScreenRoomRequest) {
		ScreenRoom screenRoom = new ScreenRoom(
			createScreenRoomRequest.cinemaId(),
			createScreenRoomRequest.screenNumber()
		);

		return screenRoomJpaRepository.save(screenRoom);
	}
}
