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

	protected DgUser(DgUserDto dto) {
		super();
		this.userId = dto.getUserId();
		this.account = dto.getAccount();
		this.password = dto.getPassword();
		this.nickname = dto.getNickname();
		this.createdDate = dto.getCreatedDate();
		this.lastModifiedDate = dto.getLastModifiedDate();

		// 갸아악 졸려
	}

	protected DgUser(String account, String password, String nickname) {
		super();

		this.account = account;
		this.password = password;
		this.nickname = nickname;
	}

	public DgUserDto toDto() {
		return new DgUserDto(this);
	}
}
