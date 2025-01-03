package devgraft.dgcinemabackend.user.app;

import devgraft.dgcinemabackend.user.domain.CreateUserRequest;
import devgraft.dgcinemabackend.user.domain.DgUserResult;

public interface DgUserUseCase {
	DgUserResult register(final CreateUserRequest request);
}
