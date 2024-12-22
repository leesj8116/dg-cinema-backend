package devgraft.dgcinemabackend.user.app;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.user.domain.CreateUserRequest;
import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;

@Service
public class DgUserApp {
	private final DgUserRepository dgUserRepository;

	public DgUserApp(DgUserRepository dgUserRepository) {
		this.dgUserRepository = dgUserRepository;
	}

	public DgUser register(CreateUserRequest request) {
		return dgUserRepository.save(DgUser.builder()
			.account(request.account())
			.password(request.password())
			.nickname(request.nickname())
			.build());
	}
}
