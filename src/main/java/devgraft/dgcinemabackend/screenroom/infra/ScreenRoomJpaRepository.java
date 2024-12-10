package devgraft.dgcinemabackend.screenroom.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devgraft.dgcinemabackend.screenroom.domain.ScreenRoom;

@Repository
public interface ScreenRoomJpaRepository extends JpaRepository<ScreenRoom, Long> {
}
