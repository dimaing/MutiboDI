package di.mutibo.server;

import di.mutibo.client.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;






import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This simple MutiboSvc allows clients to send HTTP POST requests with
 * videos that are stored in memory using a list. Clients can send HTTP GET
 * requests to receive a JSON listing of the videos that have been sent to
 * the controller so far. Stopping the controller will cause it to lose the history of
 * videos that have been sent to it because they are stored in memory.
 * 
 * Notice how much simpler this MutiboSvc is than the original VideoServlet?
 * Spring allows us to dramatically simplify our service. Another important
 * aspect of this version is that we have defined a VideoSvcApi that provides
 * strong typing on both the client and service interface to ensure that we
 * don't send the wrong paraemters, etc.
 * 
 * @author jules
 *
 */

// Tell Spring that this class is a Controller that should 
// handle certain HTTP requests for the DispatcherServlet
@Controller
public class MutiboSvc implements MutiboSvcApi {
	
		
	// Receives GET requests to /mubito and returns the next trivia set
	@RequestMapping(value=MUTIBO_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody TriviaSet getTriviaSet(){
		
			return buildTriviaSet();
	}

	private TriviaSet buildTriviaSet(){
		
		
		TriviaSet set = new TriviaSet();
		try{
			set.setSimFilms(FreebaseAPI.getFims(FreebaseAPI.QUERY_CANNES));
			set.setOddFilms(FreebaseAPI.getFims(FreebaseAPI.QUERY_VENICE));
			set.setSelectionCriteria("Cannes film festival partcipants");
		}
		catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
		}
		
		return set;
	}


}
