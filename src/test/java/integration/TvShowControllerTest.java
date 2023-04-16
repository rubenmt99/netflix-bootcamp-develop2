package integration;

import FakerObjects.FakerObject;
import com.everis.d4i.tutorial.controllers.impl.CategoryControllerImpl;
import com.everis.d4i.tutorial.controllers.impl.TvShowControllerImpl;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.services.impl.CategoryServiceImpl;
import com.everis.d4i.tutorial.services.impl.TvShowServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class TvShowControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private TvShowControllerImpl tvShowController;

	@Mock
	private TvShowServiceImpl tvShowService;


	@BeforeClass
	public static void runBeforeAllTestOfThisClass() {
		System.out.println("Run before all test of this class!");

	}

	@Before
	public void setUpMockMvc() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(tvShowController).build();
	}

	@Before
	public void runBeforeIndividualTestOfThisClass() throws Exception {
		System.out.println("Run before individual test of this class!");
	}

	@AfterClass
	public static void runAfterAllTestOfThisClass() {
		System.out.println("Run after all test of this class!");
	}

	@After
	public void runAfterIndividualTestOfThisClass() {
		System.out.println("Run after individual test of this class!");
	}

	@Test
	public void getShowByCategory() throws Exception {

		when(tvShowService.getTvShowsByCategory(1L)).thenReturn(GetMocksForTesting.getMockListTvShowRest());

		MvcResult result = mockMvc.perform(
						get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW)
								.param("categoryId","1")
								.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json("{\"status\":\"Success\",\"code\":\"200 OK\"}",false))
				.andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
				.andExpect(jsonPath("$.message", is(CommonConstants.OK)))
				.andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS)))
				.andExpect(jsonPath("$.data").exists())
				.andExpect(jsonPath("$.data.length()", is(2)))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
		verify(tvShowService, only()).getTvShowsByCategory(1L);
	}

}
