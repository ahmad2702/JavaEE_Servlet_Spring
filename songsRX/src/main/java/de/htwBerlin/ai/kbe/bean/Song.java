package de.htwBerlin.ai.kbe.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * class Song
 *
 */
@XmlRootElement(name = "song")
public class Song {

	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;

	public Song() {
	}

	/**
	 * getId
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * setId
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * getTitle
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * setTitle
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * getArtist
	 * 
	 * @return
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * setArtist
	 * 
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * getAlbum
	 * 
	 * @return
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * setAlbum
	 * 
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * getReleased
	 * 
	 * @return
	 */
	public Integer getReleased() {
		return released;
	}

	/**
	 * setReleased
	 * 
	 * @param released
	 */
	public void setReleased(Integer released) {
		this.released = released;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", artist=" + artist + ", album=" + album + ", released="
				+ released + "]";
	}
}