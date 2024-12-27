package devgraft.dgcinemabackend.cinema.app;

import java.util.List;

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
		Cinema cinema = cinemaRepository.register(Cinema.builder()
			.name(createCinemaRequest.name())
			.location(createCinemaRequest.location())
			.build());

		return new CinemaResult(cinema.getCinemaId(), cinema.getName(), cinema.getLocation());
	}

	@Override
	public List<CinemaResult> findAllCinema() {
		return cinemaRepository.findAll()
			.stream()
			.map(item -> new CinemaResult(item.getCinemaId(), item.getName(), item.getLocation()))
			.toList();
	}
}