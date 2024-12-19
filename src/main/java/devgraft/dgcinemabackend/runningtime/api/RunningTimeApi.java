package devgraft.dgcinemabackend.runningtime.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devgraft.dgcinemabackend.runningtime.app.RunningTimeApp;
import devgraft.dgcinemabackend.runningtime.domain.RunningTime;

@RestController
public class RunningTimeApi {
	private final RunningTimeApp runningTimeApp;

	public RunningTimeApi(RunningTimeApp runningTimeApp) {
		this.runningTimeApp = runningTimeApp;
	}

	/**
	 * 영화의 상영 일정을 조회한다. (영화 제목 미입력시 전체 조회)
	 * @param title
	 * @return
	 */
	@GetMapping("/running-time")
	public List<RunningTime> getRunningTimesByMovieTitle(@RequestParam(name = "title") String title) {
		return runningTimeApp.getRunningTimesByMovieTitle(title);
	}
}
