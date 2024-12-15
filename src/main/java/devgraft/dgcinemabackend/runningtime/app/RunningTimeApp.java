package devgraft.dgcinemabackend.runningtime.app;

import org.springframework.stereotype.Service;

import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;

@Service
public class RunningTimeApp {
	private final RunningTimeRepository runningTimeRepository;

	public RunningTimeApp(RunningTimeRepository runningTimeRepository) {
		this.runningTimeRepository = runningTimeRepository;
	}

}
