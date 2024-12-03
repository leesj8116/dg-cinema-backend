package devgraft.dgcinemabackend.user.domain;

import devgraft.dgcinemabackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "dg_user")
@NoArgsConstructor
@Entity
public class DgUser extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String account;     // 계정

	@Column(nullable = false)
	private String password;    // 비밀번호

	@Column(nullable = false)
	private String nickname;    // 사용자_이름

	protected DgUser(Long userId, String account, String password, String nickname) {
		super();
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.nickname = nickname;

	}
}
