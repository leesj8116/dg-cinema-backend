package devgraft.dgcinemabackend.user.domain;

import java.util.Optional;

public interface DgUserRepository {
	DgUser save(CreateUserRequest request);

	Optional<DgUser> findByAccount(String account);
}
