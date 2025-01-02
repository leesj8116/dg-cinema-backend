package devgraft.dgcinemabackend.user.domain;

import org.springframework.http.HttpStatus;

import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
	ACCOUNT_HAS_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "계정을 입력해주세요."),
	PASSWORD_HAS_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "비밀번호를 입력해주세요."),
	NICKNAME_HAS_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "사용자 이름을 입력해주세요."),
	ACCOUNT_HAS_LENGTH_UNDER_50(HttpStatus.BAD_REQUEST, "계정의 길이는 50글자 이내여야 합니다."),
	PASSWORD_HAS_LENGTH_UNDER_100(HttpStatus.BAD_REQUEST, "비밀번호의 길이는 100글자 이내여야 합니다."),
	NICKNAME_HAS_LENGTH_UNDER_30(HttpStatus.BAD_REQUEST, "사용자 이름은 30글자 이내여야 합니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
