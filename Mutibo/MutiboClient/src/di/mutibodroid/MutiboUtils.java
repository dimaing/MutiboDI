package di.mutibodroid;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;

import di.mutibo.client.MutiboSvcApi;
import di.mutibo.server.TriviaSet;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;



public class MutiboUtils {
	/** 
     * URL to the Mutibo web service.
     */
    private final static String MUTIBO_URL = "http://localhost:8080";
    
    private final static MutiboSvcApi mutiboService = new RestAdapter.Builder()
	.setEndpoint(MUTIBO_URL)
	.setLogLevel(LogLevel.FULL)
	.build()
	.create(MutiboSvcApi.class);
    
    public static TriviaSet getTriviaSet() throws Exception {
		return mutiboService.getTriviaSet();
	}
	
    
}
