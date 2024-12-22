package devgraft.dgcinemabackend.cinema.domain;

import java.util.Optional;

public interface CinemaRepository {
	Cinema register(final Cinema cinema);

	Optional<Cinema> findById(final Long id);
}
