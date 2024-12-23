package devgraft.dgcinemabackend.reservation.domain;

import java.util.Optional;

import devgraft.dgcinemabackend.cinema.domain.Cinema;

public interface CinemaFinder {
	Optional<Cinema> findById(final Long id);
}
