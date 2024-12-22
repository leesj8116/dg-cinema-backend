package devgraft.dgcinemabackend.cinema.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.CinemaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class CinemaAdapter implements CinemaRepository {
	private final CinemaJpaRepository cinemaJpaRepository;

	@Override
	public Cinema register(Cinema cinema) {
		return cinemaJpaRepository.save(cinema);
	}

	@Override
	public Optional<Cinema> findById(Long id) {
		return cinemaJpaRepository.findById(id);
	}
}
