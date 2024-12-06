package devgraft.dgcinemabackend.cinema.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devgraft.dgcinemabackend.cinema.domain.Cinema;

@Repository
public interface CinemaJpaRepository extends JpaRepository<Cinema, Long> {
}
