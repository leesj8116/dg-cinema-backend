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

	@GetMapping("/running-time")
	public List<RunningTime> getRunningTimesByMovieTitle(@RequestParam(name = "title") String title) {
		return runningTimeApp.getRunningTimesByMovieTitle(title);
	}
}
