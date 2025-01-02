package devgraft.dgcinemabackend.cinema.app;

import devgraft.dgcinemabackend.cinema.domain.CinemaErrorCode;
import devgraft.dgcinemabackend.cinema.exception.CinemaException;

public record CreateCinemaRequest(
	String name,
	String location
) {
	public CreateCinemaRequest {
		if (name == null || name.isBlank()) {
			throw new CinemaException(CinemaErrorCode.NAME_HAS_REQUIRED_FIELD);
		}
		if (location == null || location.isBlank()) {
			throw new CinemaException(CinemaErrorCode.LOCATION_HAS_REQUIRED_FIELD);
		}
		if (name.length() > 50) {
			throw new CinemaException(CinemaErrorCode.NAME_HAS_LENGTH_UNDER_50);
		}
		if (location.length() > 200) {
			throw new CinemaException(CinemaErrorCode.LOCATION_HAS_LENGTH_UNDER_200);
		}
	}
}
