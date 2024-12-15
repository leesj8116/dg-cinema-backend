package devgraft.dgcinemabackend.cinema.domain;

import java.util.Optional;

public interface CinemaRepository {
	Cinema register(CreateCinemaRequest createCinemaRequest);

	Optional<Cinema> findById(Long id);
}
