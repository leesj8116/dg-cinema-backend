package devgraft.dgcinemabackend.cinema.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.cinema.domain.Cinema;

interface CinemaJpaRepository extends JpaRepository<Cinema, Long> {
	Optional<Cinema> findById(Long id);
}
