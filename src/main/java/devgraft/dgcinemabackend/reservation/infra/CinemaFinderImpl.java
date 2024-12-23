package devgraft.dgcinemabackend.reservation.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.CinemaRepository;
import devgraft.dgcinemabackend.reservation.domain.CinemaFinder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class CinemaFinderImpl implements CinemaFinder {
	private final CinemaRepository cinemaRepository;

	@Override
	public Optional<Cinema> findById(final Long id) {
		return cinemaRepository.findById(id);
	}
}
