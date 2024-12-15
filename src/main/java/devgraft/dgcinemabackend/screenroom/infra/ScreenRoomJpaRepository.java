package devgraft.dgcinemabackend.screenroom.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;

interface ScreenRoomJpaRepository extends JpaRepository<ScreenRoom, Long> {
}
