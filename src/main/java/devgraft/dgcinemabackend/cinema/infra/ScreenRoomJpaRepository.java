package devgraft.dgcinemabackend.cinema.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.cinema.domain.ScreenRoom;

interface ScreenRoomJpaRepository extends JpaRepository<ScreenRoom, Long> {
}
