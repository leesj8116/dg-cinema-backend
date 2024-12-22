package devgraft.dgcinemabackend.cinema.app;

public record CreateCinemaRequest(
	String name,
	String location
) {
	public CreateCinemaRequest {
		if (name == null || name.isBlank())
			throw new IllegalArgumentException("극장 이름을 입력해주세요.");
		if (location == null || location.isBlank())
			throw new IllegalArgumentException("극장 주소를 입력해주세요.");
		if (name.length() > 50) {
			throw new IllegalArgumentException("극장 이름은 50글자를 넘을 수 없습니다.");
		}
		if (location.length() > 200) {
			throw new IllegalArgumentException("극장 주소는 200글자를 넘을 수 없습니다.");
		}
	}
}
