package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;

public record ReservationCancelContext(
	Long userId,
	Long reservationId
) {
	public ReservationCancelContext {
		if (userId == null) {
			// 실제 유저 정보는 입력 param은 아니겠지만..
			throw new ReservationException("유효하지 않은 사용자입니다.", CommonErrorCode.INVALID_PARAMETER);
		}
		if (reservationId == null) {
			throw new ReservationException("유효하지 않은 예약 번호입니다.", CommonErrorCode.INVALID_PARAMETER);
		}
	}
}

