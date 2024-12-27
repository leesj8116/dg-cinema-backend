package devgraft.dgcinemabackend.cinema.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.cinema.app.CinemaApp;
import devgraft.dgcinemabackend.cinema.app.CinemaResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CinemaApi {
	// @TODO: 관리자 시점 설계 : 극장 등록, 상영관 등록

	private final CinemaApp cinemaApp;

	@GetMapping("/cinema")
	public List<CinemaResult> getCinema() {
		return cinemaApp.findAllCinema();
	}
}
