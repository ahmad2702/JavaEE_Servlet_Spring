package de.htw.ai.kbe.songsServlet;

/**
 * SongStructure it will be in #SongsServlet used
 */
public class SongStructure {

	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;

	public SongStructure() {
		super();
	}

	/**
	 * @param id
	 * @param title
	 * @param artist
	 * @param album
	 * @param released
	 */
	public SongStructure(Integer id, String title, String artist, String album, Integer released) {
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
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @return released
	 */
	public Integer getReleased() {
		return released;
	}

}
