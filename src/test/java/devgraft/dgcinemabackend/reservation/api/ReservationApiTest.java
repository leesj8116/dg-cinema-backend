package devgraft.dgcinemabackend.reservation.api;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import devgraft.dgcinemabackend.reservation.app.ReservationUseCase;

/**
 * 3차 피드백 (2025.01.02)에서 또 다른 테스트 방안을 보여주고자 공유한 테스트 코드 (샘플)
 * API 테스트를 하고자 했다면, 아래와 같은 테스트 코드가 나왔어야 한다.
 */
@ExtendWith(MockitoExtension.class)
public class ReservationApiTest {
	private MockMvc mockMvc;

	@Mock
	private ReservationUseCase reservationUseCase;

	@BeforeEach
	void setUp() {
		reservationUseCase = Mockito.mock(ReservationUseCase.class);
		mockMvc = MockMvcBuilders.standaloneSetup(new ReservationApi(reservationUseCase)).build();

	}

	@Test
	void getUserReservatoins_return_value() throws Exception {
		Mockito.when(reservationUseCase.getUserReservations(Mockito.any())).thenReturn(List.of());

		mockMvc.perform(MockMvcRequestBuilders.post("/reservation/my")
				.contentType(MediaType.valueOf("application/json"))
				.content("""
					{
						"userId": 1
					}
					""")
			).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
	}
}
