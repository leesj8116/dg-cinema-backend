package devgraft.dgcinemabackend.cinema.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.CinemaRepository;
import devgraft.dgcinemabackend.cinema.domain.CreateCinemaRequest;

@Component
class CinemaAdapter implements CinemaRepository {
	private final CinemaJpaRepository cinemaJpaRepository;

	CinemaAdapter(CinemaJpaRepository cinemaJpaRepository) {
		this.cinemaJpaRepository = cinemaJpaRepository;
	}

	@Override
	public Cinema register(CreateCinemaRequest createCinemaRequest) {
		Cinema cinema = new Cinema(
			createCinemaRequest.name(),
			createCinemaRequest.address()
		);

		return cinemaJpaRepository.save(cinema);
	}

	@Override
	public Optional<Cinema> findById(Long id) {
		return cinemaJpaRepository.findById(id);
	}

}
