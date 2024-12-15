package devgraft.dgcinemabackend.user.domain;

public record CreateUserRequest(String account, String password, String nickname) {
	public CreateUserRequest {
		if (account == null || account.isBlank()) {
			throw new IllegalArgumentException("계정을 입력해주세요");
		}

		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("비밀번호를 입력해주세요");
		}

		if (nickname == null || nickname.isBlank()) {
			throw new IllegalArgumentException("사용자 이름을 입력해주세요");
		}

		if (account.length() > 50) {
			throw new IllegalArgumentException("계정은 30글자 이내로 작성하여야 합니다.");
		}

		// @TODO:  비밀번호 정책 검사
		if (password.length() > 100) {
			throw new IllegalArgumentException("비밀번호를 100글자 이내로 작성하여야 합니다.");
		}

		if (nickname.length() > 30) {
			throw new IllegalArgumentException("사용자 이름은 30글자 이내로 작성하여야 합니다.");
		}
	}
}
