package devgraft.dgcinemabackend.screenroom.app;

import java.util.Optional;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.screenroom.domain.CinemaFinder;
import devgraft.dgcinemabackend.screenroom.domain.CreateScreenRoomRequest;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoomRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScreenRoomApp {
	private final ScreenRoomRepository screenRoomRepository;
	private final CinemaFinder cinemaFinder;

	public ScreenRoomApp(ScreenRoomRepository screenRoomRepository, CinemaFinder cinemaFinder) {
		this.screenRoomRepository = screenRoomRepository;
		this.cinemaFinder = cinemaFinder;
	}

	public ScreenRoom createScreenRoom(CreateScreenRoomRequest createScreenRoomRequest) {
		// @TODO: 극장(Cinema)가 유효한 극장인지 확인해야할까?
		Optional<Cinema> cinema = cinemaFinder.findById(createScreenRoomRequest.cinemaId());
		if (cinema.isEmpty()) {
			throw new IllegalArgumentException("극장 정보가 유효하지 않습니다.");
		}

		return screenRoomRepository.register(createScreenRoomRequest);
	}

}
