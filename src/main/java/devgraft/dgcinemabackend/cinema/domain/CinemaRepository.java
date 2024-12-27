package devgraft.dgcinemabackend.cinema.domain;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository {
	Cinema register(final Cinema cinema);

	Optional<Cinema> findById(final Long id);

	List<Cinema> findAll();
}
