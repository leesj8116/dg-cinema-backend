package devgraft.dgcinemabackend.movie.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieExceptionMessage {
	TITLE_HAS_REQUIRED("영화 제목은 필수입니다."),
	TITLE_HAS_LENGTH_UNDER_200("영화 제목의 길이는 200을 넘지 않아야 합니다."),
	DIRECTOR_HAS_REQUIRED("감독 이름은 필수입니다."),
	DIRECTOR_HAS_LENGTH_UNDER_50("감독의 이름은 50자를 넘지 않아야 합니다.");

	private final String message;
}
