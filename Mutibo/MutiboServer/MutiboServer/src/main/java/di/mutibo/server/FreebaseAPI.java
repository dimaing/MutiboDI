package di.mutibo.server;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.JsonArray;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class FreebaseAPI {

	private static final String API_KEY="AIzaSyAUCTVH6icbsvJ9QkJ5jJ6GNIi1Obi5zPM";
	private static final String	FREEBASE_API_URL="https://www.googleapis.com/freebase/v1/mqlread"; 
	
	private static final String FILM_JSON =
	    "\"name\": null,"+
	    "\"tagline\": [{"+
	     " \"value\": null"+
	    "}],"+
	    "\"executive_produced_by\": [{"+
	     "\"id\": null,"+
	      "\"name\": null"+
	    "}],"+
	    "\"directed_by\": [{"+
	     " \"id\": null,"+
	     " \"name\": null"+
	    "}],"+
	    "\"country\": [{"+
	    "  \"id\": null,"+
	    "  \"name\": null"+
	    "}],"+
	    "\"starring\": [{"+
	    "  \"actor\": null"+
	    "}],"+
	    "\"trailers\": [{\"value\":null}],"+
	    "\"/common/topic/image\": [{"+
	    "  \"id\": null"+
	    "}],";
	
	
	private static final String QUERY_CANNES ="[{"+
			  "\"id\": null,"+
			  "\"name\": null,"+
			  "\"type\": \"/film/film_festival_event\","+
			  " \"festival\": \"Cannes Film Festival\","+
			  "\"first:opening_date>\": \"####\","+
			  "\"opening_date\": null,"+
			  "\"films\": [{"+FILM_JSON+
			    "\"/award/award_winning_work/awards_won\": [{"+
			    "  \"award\": [{"+
				"\"name\": null,"+
				"\"id\": null,"+
		        "\"category_of\": \"Cannes Film Festival Awards\","+
		        "\"disciplines_or_subjects|=\": [\"Film\"],"+
				"\"optional\": false"+
			      "}],"+
			      "\"optional\": false"+
			    "}]"+
			  "}],"+
			    "\"sort\": \"opening_date\","+
			    "\"limit\": 10"+
			"}]";
			
	private static final String QUERY_VENICE ="[{"+
			  "\"id\": null,"+
			  "\"name\": null,"+
			  "\"type\": \"/film/film_festival_event\","+
			  "\"venues\": \"Venice\","+
			  "\"opening_date>\": \"####\","+
			  "\"opening_date\": null,"+
			  "\"films\": [{"+
			    "\"name\": null,"+
			    "\"tagline\": [{"+
			     " \"value\": null"+
			    "}],"+
			    "\"executive_produced_by\": [{"+
			      "\"name\": null"+
			    "}],"+
			    "\"directed_by\": [{"+
			     " \"name\": null"+
			    "}],"+
			    "\"country\": [{"+
			    "  \"name\": null"+
			    "}],"+
			    "\"starring\": [{"+
			    "  \"actor\": [{\"name\": null}]"+
			    "}],"+
			    "\"trailers\": [],"+
			    "\"/common/topic/image\": [{"+
			    "  \"id\": null"+
			    "}],"+
			    "\"/award/award_winning_work/awards_won\": [{"+
			    "  \"award\": [{"+
				"\"name\": null,"+
				"\"id\": null,"+
				"\"optional\": true"+
			      "}],"+
			      "\"optional\": false"+
			    "}],"+
			    "\"limit\": 300"+
			  "}],"+
			  "\"limit\": 300"+
			"}]";
	
	public static void Sample(){
	
		try {
	
		      HttpTransport httpTransport = new NetHttpTransport();
		      HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
		      JSONParser parser = new JSONParser();
		      
		      GenericUrl url = new GenericUrl(FREEBASE_API_URL);
		      
		      url.put("query", setParams(QUERY_CANNES));
		      url.put("key", API_KEY);
		      HttpRequest request = requestFactory.buildGetRequest(url);
		      HttpResponse httpResponse = request.execute();
		      JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
		      JSONArray results = (JSONArray)response.get("result");
		      for (Object result : results) {
		    	  List<String> festival = JsonPath.read(result,"$..name");
		    	  System.out.println(festival.get(0).toString());
		    	  for(int i=0;i<10;i++){
		    		  try{
		    		  	  
		    		  String filmTag = String.format("$..films[%s].", i);
		    		  List<String> filmNames = JsonPath.read(result,filmTag+"name");
		    		  System.out.println(filmNames.get(0).toString());
		    		  List<String> tagline = JsonPath.read(result,filmTag+"tagline..value");
		    		  System.out.println(tagline.toString());
		    		  List<String> executive_produced_by = JsonPath.read(result,filmTag+"executive_produced_by..name");
		    		  System.out.println(executive_produced_by.toString());

		    		  List<String> directed_by = JsonPath.read(result,filmTag+"directed_by..name");
		    		  System.out.println(directed_by.toString());

		    		  List<String> starring = JsonPath.read(result,filmTag+"starring..actor");
		    		  System.out.println(starring.toString());
		    		  
		    		  List<String> trailers = JsonPath.read(result,filmTag+"trailers..value");
		    		  System.out.println(trailers.toString());
		    		  
		    		  List<String> pictures = JsonPath.read(result,filmTag+"/common/topic/image..id");
		    		  System.out.println(pictures.toString());
		    		  //https://usercontent.googleapis.com/freebase/v1/image/<image ID>?maxwidth=225&maxheight=225&mode=fillcropmid 


		    		  }
		    		  catch(PathNotFoundException ex){
		    			  break;
		    		  }
		    		  
		    	  }
		    	  
		    	  /*
		    	  Object films = JsonPath.read(result,"$..films");
		    	  Object film = JsonPath.read(result,"$..films[0]");
		    	  String filmName = JsonPath.read(film,"name");
		    	  
		    	  
		        System.out.println(film.toString());
		        System.out.println(films.toString());
		        System.out.println(filmName);
		        */
		      }
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
	}
	
	private static String setParams(String mql ){
		int  goBackYear = Calendar.getInstance().get(Calendar.YEAR) -  (new Random()).nextInt(50);
		return mql.replace("####",String.valueOf(goBackYear-5) ) ;
	}
	
	
}




/*
 * 
 [{
  "type": "/film/film",
  "name": null,
  "tagline": [{
    "value": null
  }],
  "executive_produced_by": [{
    "name": null
  }],
  "directed_by": [{
    "name": null
  }],
  "country": [{
    "name": null
  }],
  "starring": [{
    "actor": null
  }],
  "trailers": [],
  "/common/topic/image": [{
    "id": null
  }],
   "/common/topic/image": [{
      "id": null
    }],
  "film_festivals": [{
    "festival": "Cannes Film Festival",
    "opening_date<": "1965",
    "optional": false
  }],
  "/award/award_winning_work/awards_won": [{
    "award": [{
      "id": "/en/palme_dor",
      "optional": false
    }]
  }]
}]
 * 
 * 
[{
  "id": null,
  "name": null,
  "type": "/film/film_festival_event",
  "venues": "Cannes",
  "opening_date": null,
  "films": [{
    "name": null,
    "tagline": [{
      "value": null
    }],
    "executive_produced_by": [{
      "id": null,
      "name": null
    }],
    "directed_by": [{
      "id": null,
      "name": null
    }],
    "country": [{
      "id": null,
      "name": null
    }],
    "starring": [{
      "actor": null
    }],
    "trailers": [],
    "/common/topic/image": [{
      "id": null
    }],
    "/award/award_winning_work/awards_won": [{
      "award": [{
        "name": null,
        "id": null,
        "mid|=": [
          "/m/02wkmx",
          "/m/02vqxqn"
        ],
        "optional": false
      }],
      "optional": false
    }],
    "limit": 300000
  }],
  "limit": 100000
}]
*/

/*
 
 [{
  "id":           null,
  "name":         null,
  "type":         "/film/film_festival_event",
  "venues":       "Venice",
  "opening_date": null,
  "films": [{
    "name":     null,
    "tagline": [{
      "value": null
    }],
    "executive_produced_by": [{
      "id":   null,
      "name": null
    }],
    "directed_by": [{
      "id":   null,
      "name": null
    }],
    "country": [{
      "id":   null,
      "name": null
    }],
    "starring": [{
      "actor": null
    }],
    "trailers": [],
    "/common/topic/image": [{
      "id": null
    }],
    "/award/award_winning_work/awards_won": [{
      "award": [{
        "name":     null,
        "id|=": [
          "/m/0789r6",
          "/m/0403w16"
        ],
        "optional": false
      }],
      "optional": true
    }]
  }]
}] 



@Override
protected void onCreate(Bundle savedInstanceState)
     // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    try {
        setContentView(R.layout.videodisplay);
        String link="http://s1133.photobucket.com/albums/m590/Anniebabycupcakez/?action=view&amp; current=1376992942447_242.mp4";
        VideoView videoView = (VideoView) findViewById(R.id.VideoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video = Uri.parse(link);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
    } catch (Exception e) {
        // TODO: handle exception
        Toast.makeText(this, "Error connecting", Toast.LENGTH_SHORT).show();
    }
}


 */

