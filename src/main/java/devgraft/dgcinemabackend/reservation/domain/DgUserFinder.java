package devgraft.dgcinemabackend.reservation.domain;

import java.util.Optional;

import devgraft.dgcinemabackend.user.domain.DgUser;

public interface DgUserFinder {
	Optional<DgUser> findById(Long id);
}
