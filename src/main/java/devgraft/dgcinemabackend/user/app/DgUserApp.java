package devgraft.dgcinemabackend.user.app;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserDto;
import devgraft.dgcinemabackend.user.infra.DgUserJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DgUserApp {
	private final DgUserJpaRepository dgUserRepository;

	public DgUserDto createUser(DgUserDto user) {
		DgUser entity = dgUserRepository.save(new DgUser(user.getAccount(), user.getPassword(), user.getNickname()));

		return new DgUserDto(entity);
	}
}
