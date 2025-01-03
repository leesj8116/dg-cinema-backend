package devgraft.dgcinemabackend.user.app;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.user.domain.CreateUserRequest;
import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserRepository;
import devgraft.dgcinemabackend.user.domain.DgUserResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DgUserApp implements DgUserUseCase {
	private final DgUserRepository dgUserRepository;

	public DgUserResult register(final CreateUserRequest request) {
		DgUser user = dgUserRepository.save(DgUser.builder()
			.account(request.account())
			.password(request.password())
			.nickname(request.nickname())
			.build());

		return DgUserResult.from(user);
	}
}
