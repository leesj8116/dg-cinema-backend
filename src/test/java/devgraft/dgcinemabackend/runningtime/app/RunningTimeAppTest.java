package devgraft.dgcinemabackend.runningtime.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import devgraft.dgcinemabackend.runningtime.domain.MovieFinder;
import devgraft.dgcinemabackend.runningtime.domain.RunningTimeRepository;

@ExtendWith(MockitoExtension.class)
class RunningTimeAppTest {
	@InjectMocks
	private RunningTimeUseCase runningTimeUseCase;

	@Mock
	private MovieFinder movieFinder;

	@Mock
	private RunningTimeRepository runningTimeRepository;

	@BeforeEach
	void setUp() {
		runningTimeUseCase = new RunningTimeApp(runningTimeRepository, movieFinder);

	}

}