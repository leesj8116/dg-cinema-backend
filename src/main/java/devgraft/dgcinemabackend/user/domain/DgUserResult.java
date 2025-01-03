package devgraft.dgcinemabackend.user.domain;

public record DgUserResult(
	String account,
	String nickname
) {
	public static DgUserResult from(final DgUser dgUser) {
		return new DgUserResult(dgUser.getAccount(), dgUser.getNickname());
	}
}
