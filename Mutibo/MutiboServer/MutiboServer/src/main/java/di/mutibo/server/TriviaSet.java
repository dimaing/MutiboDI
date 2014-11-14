/**
 * 
 */
package di.mutibo.server;

import java.util.List;


/**
 * @author Dmitry I 
 *
 */
public class TriviaSet {
	private List<Film> simFilms = null;
	private List<Film> oddFilms = null;
	
	private String selectionCriteria = null;
	
	public List<Film> getSimFilms(){
		return this.simFilms;
	}

	public  void setSimFilms(List<Film> value){
		this.simFilms =  value;
	}

	
	public List<Film> getOddFilms(){
		return this.oddFilms;
	}

	public  void setOddFilms(List<Film> value){
		this.oddFilms =  value;
	}

	
	public String getSelectionCriteria(){
		return this.selectionCriteria;
	}
	
	protected void setSelectionCriteria(String value){	
		this.selectionCriteria=value;
	}

}
