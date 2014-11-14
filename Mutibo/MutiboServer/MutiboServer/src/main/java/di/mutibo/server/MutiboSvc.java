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
			set.setSelectionCriteria("Cannes film festival film partcipants");
		}
		catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
		}
		
		return set;
	}


}
