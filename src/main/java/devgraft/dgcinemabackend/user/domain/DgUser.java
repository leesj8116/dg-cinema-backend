package devgraft.dgcinemabackend.user.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Table(name = "dg_user")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DgUser extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, length = 50)
	private String account;     // 계정

	@Column(nullable = false, length = 100)
	private String password;    // 비밀번호

	@Column(nullable = false, length = 30)
	private String nickname;    // 사용자_이름
}
