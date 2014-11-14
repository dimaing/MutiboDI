import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import di.mutibo.*;
import di.mutibo.client.MutiboSvcApi;
import di.mutibo.server.MutiboSvc;
import di.mutibo.server.TriviaSet;

public class MutiboSvcClientTest {
	private final String TEST_URL = "http://localhost:8080";

	/**
	 * This is how we turn the VideoSvcApi into an object that
	 * will translate method calls on the VideoSvcApi's interface
	 * methods into HTTP requests on the server. Parameters / return
	 * values are being marshalled to/from JSON.
	 */
	private MutiboSvcApi mutiboService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL)
			.setLogLevel(LogLevel.FULL)
			.build()
			.create(MutiboSvcApi.class);

	/**
	 * This test sends a POST request to the VideoServlet to add a new video and
	 * then sends a second GET request to check that the video showed up in the
	 * list of videos.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetTriviaSet() throws Exception {
		TriviaSet set = mutiboService.getTriviaSet();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("mutiboService.getTriviaSet as JSON=<<" + mapper.writeValueAsString(set) + ">>");
		assertTrue(set.getOddFilms()!=null &&  !set.getOddFilms().isEmpty());
		assertTrue(set.getSimFilms()!=null &&  !set.getSimFilms().isEmpty());

	}
	
	
	
}
