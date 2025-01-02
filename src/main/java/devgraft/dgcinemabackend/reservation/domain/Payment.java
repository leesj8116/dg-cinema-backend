package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Getter
@Builder
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;                     // 결제 아이디

	@Column(nullable = false)
	private Integer amount;                     // 금액

	@Column(nullable = false)
	@Builder.Default
	private Boolean result = Boolean.FALSE;     // 결제 상태

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;            // 예약 정보
}
