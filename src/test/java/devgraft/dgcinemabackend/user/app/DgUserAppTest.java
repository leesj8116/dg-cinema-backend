package devgraft.dgcinemabackend.user.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import devgraft.dgcinemabackend.user.domain.DgUserDto;
import devgraft.dgcinemabackend.user.infra.DgUserJpaRepository;

@SpringBootTest()
class DgUserAppTest {

	private final DgUserApp dgUserApp;
	private final DgUserJpaRepository dgUserJpaRepository;

	@Autowired
	public DgUserAppTest(DgUserApp dgUserApp, DgUserJpaRepository dgUserJpaRepository) {
		this.dgUserApp = dgUserApp;
		this.dgUserJpaRepository = dgUserJpaRepository;
	}

	@AfterEach
	void clearUser() {
		dgUserJpaRepository.deleteAll();
	}

	@Test
	@DisplayName("유저를 등록한다")
	void createUser() {
		// given
		DgUserDto userDto = DgUserDto.builder()
			.account("test@test.com")
			.password("test_password")
			.nickname("테스트_계정")
			.build();

		// when
		dgUserApp.createUser(userDto);

		// then
		Assertions.assertThat(dgUserJpaRepository.findByAccount("test@test.com").isPresent()).isTrue();
	}

	@Test
	@DisplayName("사용자 계정은 중복될 수 없다")
	void duplicateUser() {
		// given
		DgUserDto userDto = DgUserDto.builder()
			.account("test@test.com")
			.password("test_password")
			.nickname("테스트_계정")
			.build();

		DgUserDto duplicateDto = DgUserDto.builder()
			.account("test@test.com")
			.password("test_password2")
			.nickname("중복된_계정")
			.build();

		// when
		dgUserApp.createUser(userDto);

		// then
		Assertions.assertThatThrownBy(() -> dgUserApp.createUser(duplicateDto))
			.isInstanceOf(DataIntegrityViolationException.class);
	}
}
