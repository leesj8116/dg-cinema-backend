package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.common.exception.CommonErrorCode;
import devgraft.dgcinemabackend.reservation.exception.ReservationException;

public record ReservationContext(
	Long userId,
	Long runningTimeId,
	String seatNo
) {
	public ReservationContext {
		if (userId == null) {
			// 실제 유저 정보는 입력 param은 아니겠지만..
			throw new ReservationException("유효하지 않은 사용자입니다.", CommonErrorCode.INVALID_PARAMETER);
		}
		if (runningTimeId == null) {
			throw new ReservationException("유효하지 않은 상영 시간 정보입니다.", CommonErrorCode.INVALID_PARAMETER);
		}
		if (!isValidSeatNo(seatNo)) {
			throw new ReservationException("유효하지 않은 좌석 입력입니다. : " + seatNo, CommonErrorCode.INVALID_PARAMETER);
		}
	}

	private boolean isValidSeatNo(String seatNo) {
		return seatNo != null && seatNo.matches("^[A-H](1[0-2]|[1-9])$");
	}
}
