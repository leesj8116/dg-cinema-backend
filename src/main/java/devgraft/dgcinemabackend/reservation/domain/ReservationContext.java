package devgraft.dgcinemabackend.reservation.domain;

public record ReservationContext(
	Long userId,
	Long runningTimeId,
	String seatNo
) {
	public ReservationContext {
		if (userId == null) {
			// 실제 유저 정보는 입력 param은 아니겠지만..
			throw new IllegalArgumentException("사용자 정보가 유효하지 않습니다.");
		}
		if (!isValidSeatNo(seatNo)) {
			throw new IllegalArgumentException("유효하지 않은 좌석 입력입니다. : " + seatNo);
		}
	}

	private boolean isValidSeatNo(String seatNo) {
		return seatNo != null && seatNo.matches("^[A-H](1[0-2]|[1-9])$");
	}
}
