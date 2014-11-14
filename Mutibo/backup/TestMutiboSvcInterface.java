
package di.mutibodroid.test;

import static org.junit.Assert.*;

import di.mutibo.client.MutiboSvcApi;
import di.mutibo.server.TriviaSet;
import di.mutibodroid.MutiboUtils;
import org.junit.Test;


public class TestMutiboSvcInterface {

	@Test
	public void testMutiboSvc() {
		TriviaSet set = MutiboUtils.getTriviaSet();
		assertTrue(set.getOddFilms()!=null &&  !set.getOddFilms().isEmpty());
		assertTrue(set.getSimFilms()!=null &&  !set.getSimFilms().isEmpty());		
	}

}
