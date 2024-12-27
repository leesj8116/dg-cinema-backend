package devgraft.dgcinemabackend.cinema.app;

import java.util.List;

public interface CinemaUseCase {
	CinemaResult createCinema(final CreateCinemaRequest createCinemaRequest);

	List<CinemaResult> findAllCinema();
}
