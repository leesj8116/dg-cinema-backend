package devgraft.dgcinemabackend.reservation.domain;

public record GetMyReservationRequest(
	Long userId
) {
	public GetMyReservationRequest {
		if (userId == null) {
			// 실제 유저 정보는 입력 param은 아니겠지만..
			throw new IllegalArgumentException("유효하지 않은 요청 값 입니다.");
		}
	}
}
