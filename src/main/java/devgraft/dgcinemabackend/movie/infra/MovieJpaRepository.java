package devgraft.dgcinemabackend.movie.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.movie.domain.Movie;

interface MovieJpaRepository extends JpaRepository<Movie, Long> {

}
