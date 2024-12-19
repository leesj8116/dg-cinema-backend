package devgraft.dgcinemabackend.user.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import devgraft.dgcinemabackend.user.domain.DgUser;

interface DgUserJpaRepository extends JpaRepository<DgUser, Long> {
	Optional<DgUser> findByAccount(String account);
	Optional<DgUser> findById(Long id);
}
