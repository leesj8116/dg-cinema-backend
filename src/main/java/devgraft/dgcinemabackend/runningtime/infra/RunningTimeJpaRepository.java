package devgraft.dgcinemabackend.runningtime.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

@Repository
public interface RunningTimeJpaRepository extends JpaRepository<RunningTime, Long> {
}
