package devgraft.dgcinemabackend.runningtime.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

interface RunningTimeJpaRepository extends JpaRepository<RunningTime, Long> {
}
