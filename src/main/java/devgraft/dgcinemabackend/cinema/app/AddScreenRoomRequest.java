package devgraft.dgcinemabackend.cinema.app;

public record AddScreenRoomRequest(
	Long screenNumber,
	String screenRoomName
) {
}
