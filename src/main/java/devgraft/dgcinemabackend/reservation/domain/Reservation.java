package devgraft.dgcinemabackend.reservation.domain;

import org.hibernate.annotations.DynamicUpdate;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;
import devgraft.dgcinemabackend.user.domain.DgUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
public class Reservation extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;             // 아이디

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private DgUser user;                    // 예약 사용자

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "runningTime_id")
	private RunningTime runningTime;        // 상영시간

	// @TODO: 좌석 번호 체계 구성
	@Column(nullable = false, length = 10)
	private String seatNo;                  // 좌석번호

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private ReservationStatus status = ReservationStatus.PENDING;   // 예약 상태

	// @TODO: 이 메소드가 적절한지 다시 검토 필요
	public void modifyStatus(ReservationStatus newStatus) {
		this.status = newStatus;
	}
}
