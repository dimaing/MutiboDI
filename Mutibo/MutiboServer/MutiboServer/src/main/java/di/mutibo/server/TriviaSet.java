/**
 * 
 */
package di.mutibo.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import com.google.common.base.Objects;

/**
 * @author Dmitry I 
 *
 */
public class TriviaSet {
	private List<Film> _simFilms = null;
	private List<Film> _oddFilms = null;
	
	private String _selectionCriteria = null;
	
	public List<Film> getSimFilms(){
		return _simFilms;
	}

	public  void setSimFilms(List<Film> value){
		_simFilms =  value;
	}

	
	public List<Film> getOddFilms(){
		return _oddFilms;
	}

	public  void setOddFilms(List<Film> value){
		_oddFilms =  value;
	}

	
	public String getSelectionCriteria(){
		return _selectionCriteria;
	}
	
	protected void setSelectionCriteria(String value){	
		_selectionCriteria=value;
	}

}
