package devgraft.dgcinemabackend.user.domain;

import java.util.Optional;

public interface DgUserRepository {
	DgUser save(final DgUser user);

	Optional<DgUser> findByAccount(final String account);

	Optional<DgUser> findById(final Long id);
}
