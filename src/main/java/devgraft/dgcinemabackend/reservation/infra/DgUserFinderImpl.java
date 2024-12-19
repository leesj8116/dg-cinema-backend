package devgraft.dgcinemabackend.reservation.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;

@Component
class DgUserFinderImpl implements DgUserFinder {
	private final DgUserRepository dgUserRepository;

	DgUserFinderImpl(DgUserRepository dgUserRepository) {
		this.dgUserRepository = dgUserRepository;
	}

	@Override
	public Optional<DgUser> findById(Long id) {
		return dgUserRepository.findById(id);
	}
}
