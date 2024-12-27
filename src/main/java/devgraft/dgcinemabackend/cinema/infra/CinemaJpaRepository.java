package devgraft.dgcinemabackend.cinema.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.cinema.domain.Cinema;

interface CinemaJpaRepository extends JpaRepository<Cinema, Long> {
	Optional<Cinema> findById(final Long id);
	List<Cinema> findAll();
}
