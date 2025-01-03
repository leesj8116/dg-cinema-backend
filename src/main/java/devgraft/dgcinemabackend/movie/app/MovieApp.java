package devgraft.dgcinemabackend.movie.app;

import java.util.List;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.movie.domain.MovieRepository;
import devgraft.dgcinemabackend.movie.domain.MovieResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class MovieApp implements MovieUseCase {
	private final MovieRepository movieRepository;

	public List<MovieResult> getMovies() {
		return movieRepository.findAllByOrderByReleaseDateAsc()
			.stream()
			.map(movie -> MovieResult.from(movie))
			.toList();
	}
}
