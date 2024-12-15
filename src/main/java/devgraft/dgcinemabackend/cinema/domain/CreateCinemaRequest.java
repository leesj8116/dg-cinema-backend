package devgraft.dgcinemabackend.cinema.domain;

public record CreateCinemaRequest(
	String name,
	String address
) {
	public CreateCinemaRequest {
		if (name == null || name.isBlank())
			throw new IllegalArgumentException("극장 이름을 입력해주세요.");
		if (address == null || address.isBlank())
			throw new IllegalArgumentException("극장 주소를 입력해주세요.");
		if (name.length() > 50) {
			throw new IllegalArgumentException("극장 이름은 50글자를 넘을 수 없습니다.");
		}
		if (address.length() > 200) {
			throw new IllegalArgumentException("극장 주소는 200글자를 넘을 수 없습니다.");
		}
	}
}
