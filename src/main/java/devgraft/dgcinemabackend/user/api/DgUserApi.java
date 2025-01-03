package devgraft.dgcinemabackend.user.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.user.app.DgUserUseCase;
import devgraft.dgcinemabackend.user.domain.CreateUserRequest;
import devgraft.dgcinemabackend.user.domain.DgUserResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class DgUserApi {
	private final DgUserUseCase userUseCase;

	/**
	 * 유저 생성
	 *
	 * @param createUserRequest
	 * @return
	 */
	@PostMapping("/user")
	public DgUserResult createUser(CreateUserRequest createUserRequest) {
		return userUseCase.register(createUserRequest);
	}
}
