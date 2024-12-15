package devgraft.dgcinemabackend.screenroom.domain;

import java.util.Optional;

import devgraft.dgcinemabackend.cinema.domain.Cinema;

public interface CinemaFinder {
	Optional<Cinema> findById(Long id);
}
