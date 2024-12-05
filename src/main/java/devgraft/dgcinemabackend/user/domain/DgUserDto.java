package devgraft.dgcinemabackend.user.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DgUserDto {
	private Long userId;

	@NotBlank(message = "계정은 필수 입력 값입니다.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	private String account;

	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	private String password;

	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2 ~ 10자리여야 합니다.")
	private String nickname;

	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

	public DgUserDto(DgUser entity) {
		this.userId = entity.getUserId();
		this.account = entity.getAccount();
		// @TODO: 비밀번호 평문 -> 암호문 관리
		this.password = entity.getPassword();
		this.nickname = entity.getNickname();
		this.createdDate = entity.getCreatedDate();
		this.lastModifiedDate = entity.getLastModifiedDate();
	}
}
