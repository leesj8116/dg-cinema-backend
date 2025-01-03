package devgraft.dgcinemabackend.user.domain;

public record CreateUserRequest(String account, String password, String nickname) {
	public CreateUserRequest {
		if (account == null || account.isBlank()) {
			throw new UserException(UserErrorCode.ACCOUNT_HAS_REQUIRED_FIELD);
		}

		if (password == null || password.isBlank()) {
			throw new UserException(UserErrorCode.PASSWORD_HAS_REQUIRED_FIELD);
		}

		if (nickname == null || nickname.isBlank()) {
			throw new UserException(UserErrorCode.NICKNAME_HAS_REQUIRED_FIELD);
		}

		if (account.length() > 50) {
			throw new UserException(UserErrorCode.ACCOUNT_HAS_LENGTH_UNDER_50);
		}

		// @TODO:  비밀번호 정책 검사
		if (password.length() > 100) {
			throw new UserException(UserErrorCode.PASSWORD_HAS_LENGTH_UNDER_100);
		}

		if (nickname.length() > 30) {
			throw new UserException(UserErrorCode.NICKNAME_HAS_LENGTH_UNDER_30);
		}
	}
}
