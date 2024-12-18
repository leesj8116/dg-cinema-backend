package devgraft.dgcinemabackend.movie.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.movie.domain.Movie;

interface MovieJpaRepository extends JpaRepository<Movie, Long> {
	List<Movie> findAllByOrderByReleaseDateAsc();
	List<Movie> findAllByTitleContainingOrderByReleaseDateAsc(String title);
}
