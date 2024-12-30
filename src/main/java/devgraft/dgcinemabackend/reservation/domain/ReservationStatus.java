package devgraft.dgcinemabackend.reservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationStatus {
	PENDING("대기"),
	SUCCESS("완료"),
	CANCEL("취소"),
	EXPIRED("시간 만료");

	private final String description;
}
