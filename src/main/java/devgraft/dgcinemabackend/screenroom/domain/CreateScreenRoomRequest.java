package devgraft.dgcinemabackend.screenroom.domain;

public record CreateScreenRoomRequest(
	Long cinemaId,
	Long screenNumber
) {
	public CreateScreenRoomRequest {
		if (cinemaId == null || screenNumber == null) {
			throw new IllegalArgumentException("필드를 입력해주세요.");
		}
		if (screenNumber <= 0) {
			throw new IllegalArgumentException("상영관 번호는 1 이상의 양수만 가능합니다.");
		}
	}
}
