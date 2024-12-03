package devgraft.dgcinemabackend.user.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devgraft.dgcinemabackend.user.domain.DgUser;

@Repository
public interface DgUserJpaRepository extends JpaRepository<DgUser, Long> {
	Optional<DgUser> findByAccount(String account);
}
