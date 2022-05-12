package rmi.interphace;

import java.io.Serializable;


public class MoviePackage implements Serializable
{
	
	private static final long serialVersionUID = 6395897405599741380L;
	
	private String _title;
	private String _genre;
	private String _description;
	private String _year;
	private String _quality;
	private String _rating;
	private String _duration;
	private String _trailer;
	

	public MoviePackage(String title, String genre, String description, String year, String quality,
			String rating, String duration, String trailer)
	{
		this._title = title;
		this._genre = genre;
		this._description = description;
		this._year = year;
		this._quality = quality;
		this._rating = rating;
		this._duration = duration;
		this._trailer = trailer;
	}


	public String get_title() {
		return _title;
	}


	public String get_genre() {
		return _genre;
	}


	public String get_description() {
		return _description;
	}


	public String get_year() {
		return _year;
	}


	public String get_quality() {
		return _quality;
	}


	public String get_rating() {
		return _rating;
	}


	public String get_duration() {
		return _duration;
	}


	public String get_trailer() {
		return _trailer;
	}
	

}
