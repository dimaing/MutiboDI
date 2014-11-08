package di.mutibo.server;

import java.util.List;



import com.google.common.base.Objects;



public class Film {

	
	private long id;

	private List<String> names;
	private List<String> urls;
	private List<String> taglines;
	private List<String> directors;
	private List<String> producers;
	private List<String> stars;
	private List<String> pictures;
	private List<String> trailers;
	

	public Film() {
	}


	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getTaglines() {
		return taglines;
	}
	public void setTaglines(List<String> taglines) {
		this.taglines = taglines;
	}

	public List<String> getDirectors() {
		return directors;
	}
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<String> getProducers() {
		return producers;
	}
	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public List<String> getStars() {
		return stars;
	}
	public void setStars(List<String> stars) {
		this.stars = stars;
	}

	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public List<String> getTrailers() {
		return trailers;
	}
	public void setTrailers(List<String> trailers) {
		this.pictures = trailers;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Two Videos will generate the same hashcode if they have exactly the same
	 * values for their name, url, and duration.
	 * 
	 */
	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(names.get(0));
	}

	/**
	 * Two Videos are considered equal if they have exactly the same values for
	 * their name, url, and duration.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Film) {
			Film other = (Film) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(names.get(0), other.names.get(0));
		} else {
			return false;
		}
	}

}
