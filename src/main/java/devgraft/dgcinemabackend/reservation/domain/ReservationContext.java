package devgraft.dgcinemabackend.reservation.domain;

public record ReservationContext(Long userId, Long runningTimeId, String seetNo) {
	public ReservationContext {
		if (userId == null) {
			throw new IllegalArgumentException("사용자 정보가 유효하지 않습니다.");
		}
		if (!isValidSeatNo(seetNo)) {
			throw new IllegalArgumentException("유효하지 않은 좌석 입력입니다. : " + seetNo);
		}
	}

	private boolean isValidSeatNo(String seatNo) {
		return seatNo != null && seatNo.matches("^[A-H](1[0-2]|[1-9])$");
	}
}
