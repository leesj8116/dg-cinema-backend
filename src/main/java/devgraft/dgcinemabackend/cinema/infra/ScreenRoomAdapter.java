package devgraft.dgcinemabackend.cinema.infra;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.cinema.domain.ScreenRoom;
import devgraft.dgcinemabackend.cinema.domain.ScreenRoomRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScreenRoomAdapter implements ScreenRoomRepository {
	private final ScreenRoomJpaRepository screenRoomJpaRepository;

	@Override
	public ScreenRoom register(ScreenRoom screenRoom) {
		return screenRoomJpaRepository.save(screenRoom);
	}
}
