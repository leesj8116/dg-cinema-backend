package devgraft.dgcinemabackend.screenroom.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoomDto;
import devgraft.dgcinemabackend.screenroom.infra.ScreenRoomJpaRepository;

@Service
public class ScreenRoomApp {
	private final ScreenRoomJpaRepository screenRoomJpaRepository;

	@Autowired
	public ScreenRoomApp(ScreenRoomJpaRepository screenRoomJpaRepository) {
		this.screenRoomJpaRepository = screenRoomJpaRepository;
	}

	public ScreenRoomDto createScreenRoom(ScreenRoomDto screenRoomDto) {
		ScreenRoom entity = screenRoomJpaRepository.save(
			new ScreenRoom(screenRoomDto.getCinemaId(), screenRoomDto.getScreenNumber()));

		return new ScreenRoomDto(entity);
	}

}
