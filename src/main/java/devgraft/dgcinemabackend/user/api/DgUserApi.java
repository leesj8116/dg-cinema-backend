package devgraft.dgcinemabackend.user.api;

import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.user.domain.DgUser;
import devgraft.dgcinemabackend.user.domain.DgUserDto;
import devgraft.dgcinemabackend.user.infra.DgUserJpaRepository;

@RestController
public class DgUserApi {
	private final DgUserJpaRepository dgUserJpaRepository;

	public DgUserApi(DgUserJpaRepository dgUserJpaRepository) {
		this.dgUserJpaRepository = dgUserJpaRepository;
	}

	/**
	 * 유저 생성
	 *
	 * @param user
	 * @return
	 */
	public DgUser createUser(DgUserDto user) {
		return dgUserJpaRepository.save(user.toEntity());
	}
}
