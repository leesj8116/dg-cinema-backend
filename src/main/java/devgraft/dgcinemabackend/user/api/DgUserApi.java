package devgraft.dgcinemabackend.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.user.app.DgUserApp;
import devgraft.dgcinemabackend.user.domain.DgUserDto;

@RestController
public class DgUserApi {
	private final DgUserApp dgUserApp;

	@Autowired
	public DgUserApi(DgUserApp dgUserApp) {
		this.dgUserApp = dgUserApp;
	}

	/**
	 * 유저 생성
	 *
	 * @param user
	 * @return
	 */
	@PostMapping("/user")
	public DgUserDto createUser(DgUserDto user) {
		return dgUserApp.createUser(user);
	}
}
