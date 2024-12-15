package devgraft.dgcinemabackend.screenroom.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.cinema.app.CinemaApp;
import devgraft.dgcinemabackend.cinema.domain.Cinema;
import devgraft.dgcinemabackend.screenroom.domain.CinemaFinder;

@Component
public class CinemaFinderImpl implements CinemaFinder {
	private final CinemaApp cinemaApp;

	public CinemaFinderImpl(CinemaApp cinemaApp) {
		this.cinemaApp = cinemaApp;
	}

	@Override
	public Optional<Cinema> findById(Long id) {
		return cinemaApp.findById(id);
	}
}
