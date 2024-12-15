package devgraft.dgcinemabackend.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.user.app.DgUserApp;
import devgraft.dgcinemabackend.user.domain.CreateUserRequest;
import devgraft.dgcinemabackend.user.domain.DgUser;

@RestController
public class DgUserApi {
	private final DgUserApp userApp;

	@Autowired
	public DgUserApi(DgUserApp userApp) {
		this.userApp = userApp;
	}

	/**
	 * 유저 생성
	 *
	 * @param createUserRequest
	 * @return
	 */
	@PostMapping("/user")
	public ResponseEntity<DgUser> createUser(CreateUserRequest createUserRequest) {
		return ResponseEntity.ok(userApp.register(createUserRequest));
	}
}
