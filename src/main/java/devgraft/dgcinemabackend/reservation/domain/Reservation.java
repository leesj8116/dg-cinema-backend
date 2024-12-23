package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;             // 아이디

	@ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private DgUser user;                    // 예약 사용자

	@ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
	@JoinColumn(name = "runningTime_id")
	private RunningTime runningTime;        // 상영시간

	// @TODO: 좌석 번호 체계 구성
	@Column(nullable = false, length = 10)
	private String seatNo;                  // 좌석번호
}
