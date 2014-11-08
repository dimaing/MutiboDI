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
	private List<Film> _films = new CopyOnWriteArrayList<Film>();  
	private String _selectionCriteria = null;
	
	public List<Film> getFilms(){
		
		return _films;
	}
	
	public void addFilm(Film film){
		_films.add(film);
	
	}
	
	public String getSelectionCriteria(){
		return _selectionCriteria;
	}
	
	protected void setSelectionCriteria(String value){
		
		_selectionCriteria=value;
	}

}
