package devgraft.dgcinemabackend.reservation.infra;

import java.util.Optional;

import org.springframework.stereotype.Component;

import devgraft.dgcinemabackend.reservation.domain.DgUserFinder;
import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class DgUserFinderImpl implements DgUserFinder {
	private final DgUserRepository dgUserRepository;

	@Override
	public Optional<DgUser> findById(final Long id) {
		return dgUserRepository.findById(id);
	}
}
