
import di.mutibo.*; 
import di.mutibo.server.*;
import di.mutibo.client.*;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class MutiboSvcTest {
	
	private MutiboSvc _mutiboSvc = new MutiboSvc();

	/**
	 * This test requests trivia set from service  
	 * @throws Exception
	 */
	@Test
	public void testTriviaSetRequest() throws Exception {
		TriviaSet set = _mutiboSvc.getTriviaSet();
		assertTrue(set.getOddFilms()!=null &&  !set.getOddFilms().isEmpty());
		assertTrue(set.getSimFilms()!=null &&  !set.getSimFilms().isEmpty());
	}

}