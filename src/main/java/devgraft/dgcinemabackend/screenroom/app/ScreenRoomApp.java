package devgraft.dgcinemabackend.screenroom.app;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.app.CinemaApp;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;
import devgraft.dgcinemabackend.screenroom.domain.ScreenRoomDto;
import devgraft.dgcinemabackend.screenroom.infra.ScreenRoomJpaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScreenRoomApp {
	private final ScreenRoomJpaRepository screenRoomJpaRepository;
	private final CinemaApp cinemaApp;

	@Autowired
	public ScreenRoomApp(ScreenRoomJpaRepository screenRoomJpaRepository, CinemaApp cinemaApp) {
		this.screenRoomJpaRepository = screenRoomJpaRepository;
		this.cinemaApp = cinemaApp;
	}

	public ScreenRoomDto createScreenRoom(ScreenRoomDto screenRoomDto) {
		// Cinema가 유효한지 검사
		try {
			cinemaApp.findById(screenRoomDto.getCinemaId())
				.orElseThrow();
		} catch (NoSuchElementException e) {
			log.error("등록되지 않은 극장 아이디입니다.");
			throw new IllegalArgumentException("유효한 cinemaId를 입력해야 합니다.");
		}

		ScreenRoom entity = screenRoomJpaRepository.save(
			new ScreenRoom(screenRoomDto.getCinemaId(), screenRoomDto.getScreenNumber()));

		return new ScreenRoomDto(entity);
	}

}
