package devgraft.dgcinemabackend.reservation.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
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

@Getter
@Builder
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;                             // 결제 아이디

	@Column(nullable = false)
	private Integer amount;                             // 금액

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private PaymentType type = PaymentType.PURCHASE;    // 구분 (구매 | 환불)

	@Column(nullable = false)
	private Boolean success;                            // 성공 | 실패

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;                    // 예약 정보
}
