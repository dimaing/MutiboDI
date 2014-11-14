package di.mutibodroid.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import di.mutibo.server.TriviaSet;
import di.mutibodroid.MutiboUtils;

public class MutiboUtilsTest {

	@Test
	public final void testGetTriviaSet() {
		try {
			TriviaSet set = MutiboUtils.getTriviaSet();
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("mutiboService.getTriviaSet as JSON=<<" + mapper.writeValueAsString(set) + ">>");
			assertTrue(set.getOddFilms()!=null &&  !set.getOddFilms().isEmpty());
			assertTrue(set.getSimFilms()!=null &&  !set.getSimFilms().isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
