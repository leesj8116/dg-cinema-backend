package devgraft.dgcinemabackend.user.domain;

import java.util.Optional;

public interface DgUserRepository {
	DgUser save(final DgUser user);

	Optional<DgUser> findByAccount(String account);

	Optional<DgUser> findById(Long id);
}
