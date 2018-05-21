package de.htw.ai.kbe.songsServlet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Song is a structure for one song
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "song")
public class Song {
	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;

	public Song() {
		super();
	}

	/**
	 * @param id
	 * @param title
	 * @param artist
	 * @param album
	 * @param released
	 */
	public Song(Integer id, String title, String artist, String album, Integer released) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.released = released;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return released
	 */
	public Integer getReleased() {
		return released;
	}

	/**
	 * @param released
	 */
	public void setReleased(Integer released) {
		this.released = released;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [id=" + id + ", title=" + title + ", artist=" + artist + ", album=" + album + ", released=" + released
				+ "] ";
	}

}
