package devgraft.dgcinemabackend.movie.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.movie.domain.Movie;
import devgraft.dgcinemabackend.movie.domain.MovieDto;
import devgraft.dgcinemabackend.movie.infra.MovieJpaRepository;

@Service
public class MovieApp {
	private final MovieJpaRepository movieJpaRepository;

	@Autowired
	public MovieApp(MovieJpaRepository movieJpaRepository) {
		this.movieJpaRepository = movieJpaRepository;
	}

	public MovieDto createMovie(MovieDto movieDto) {
		Movie entity = movieJpaRepository.save(
			new Movie(movieDto.getTitle(), movieDto.getDirector(), movieDto.getReleaseDate()));

		return new MovieDto(entity);
	}

	public List<MovieDto> getAllMovies() {
		List<MovieDto> movies = movieJpaRepository.findAll()
			.stream()
			.map(m -> new MovieDto(m))
			.collect(Collectors.toList());

		return movies;
	}
}
