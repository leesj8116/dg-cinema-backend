package devgraft.dgcinemabackend.user.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class DgUserAdapter implements DgUserRepository {
	private final DgUserJpaRepository dgUserJpaRepository;

	@Override
	public DgUser save(final DgUser user) {
		return dgUserJpaRepository.save(user);
	}

	@Override
	public Optional<DgUser> findByAccount(final String account) {
		return dgUserJpaRepository.findByAccount(account);
	}

	@Override
	public Optional<DgUser> findById(final Long id) {
		return dgUserJpaRepository.findById(id);
	}
}
