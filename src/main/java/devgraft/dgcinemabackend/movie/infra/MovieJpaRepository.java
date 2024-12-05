package devgraft.dgcinemabackend.movie.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devgraft.dgcinemabackend.movie.domain.Movie;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
}
