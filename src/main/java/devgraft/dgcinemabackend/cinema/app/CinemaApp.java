package devgraft.dgcinemabackend.cinema.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.cinema.domain.CinemaRepository;
import devgraft.dgcinemabackend.cinema.domain.CreateCinemaRequest;

@Service
public class CinemaApp {
	private final CinemaRepository cinemaRepository;

	@Autowired
	public CinemaApp(CinemaRepository cinemaRepository) {
		this.cinemaRepository = cinemaRepository;
	}

	public Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
		return cinemaRepository.register(createCinemaRequest);
	}

	public Optional<Cinema> findById(Long id) {
		return cinemaRepository.findById(id);
	}
}