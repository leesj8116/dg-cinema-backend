package devgraft.dgcinemabackend.cinema.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.CinemaDto;
import devgraft.dgcinemabackend.cinema.infra.CinemaJpaRepository;

@Service
public class CinemaApp {
	private final CinemaJpaRepository cinemaJpaRepository;

	@Autowired
	public CinemaApp(CinemaJpaRepository cinemaJpaRepository) {
		this.cinemaJpaRepository = cinemaJpaRepository;
	}

	public CinemaDto createCinema(CinemaDto cinema) {
		Cinema entity = cinemaJpaRepository.save(new Cinema(cinema.getName(), cinema.getLocation()));

		return new CinemaDto(entity);
	}
}