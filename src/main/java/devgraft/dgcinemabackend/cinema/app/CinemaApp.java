package devgraft.dgcinemabackend.cinema.app;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.CinemaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CinemaApp implements CinemaUseCase {
	private final CinemaRepository cinemaRepository;

	@Override
	public CinemaResult createCinema(CreateCinemaRequest createCinemaRequest) {

		Cinema cinemaEntity = cinemaRepository.register(Cinema.builder()
			.name(createCinemaRequest.name())
			.location(createCinemaRequest.location())
			.build());

		return new CinemaResult(cinemaEntity.getName(), cinemaEntity.getLocation());
	}
}