package di.mutibo.client;

import di.mutibo.server.Film;
import di.mutibo.server.TriviaSet;

import java.util.Collection;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * This interface defines an API for a VideoSvc. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 * interface into a client object. See VideoSvcClientApiTest
 * for an example of how Retrofit is used to turn this interface
 * into a client.
 * 
 * @author jules
 *
 */
public interface MutiboSvcApi {
	
	// The path where we expect the VideoSvc to live
	public static final String MUTIBO_SVC_PATH = "/mutibo";

	@GET(MUTIBO_SVC_PATH)
	public TriviaSet getTriviaSet();
	
	
}

	

