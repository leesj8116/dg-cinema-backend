package devgraft.dgcinemabackend.cinema.domain;

import org.springframework.http.HttpStatus;

import devgraft.dgcinemabackend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CinemaErrorCode implements ErrorCode {
	NAME_HAS_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "극장 이름을 입력해주세요."),
	LOCATION_HAS_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "극장 주소를 입력해주세요."),
	NAME_HAS_LENGTH_UNDER_50(HttpStatus.BAD_REQUEST, "극장 이름은 50글자를 넘을 수 없습니다."),
	LOCATION_HAS_LENGTH_UNDER_200(HttpStatus.BAD_REQUEST, "극장 주소는 200글자를 넘을 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
