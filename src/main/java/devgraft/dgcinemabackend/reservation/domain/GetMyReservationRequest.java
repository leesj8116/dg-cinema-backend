package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;

public record GetMyReservationRequest(
	Long userId
) {
	public GetMyReservationRequest {
		if (userId == null) {
			// 실제 유저 정보는 입력 param은 아니겠지만..
			throw new ReservationException("유효하지 않은 요청 값 입니다.", CommonErrorCode.INVALID_PARAMETER);
		}
	}
}
