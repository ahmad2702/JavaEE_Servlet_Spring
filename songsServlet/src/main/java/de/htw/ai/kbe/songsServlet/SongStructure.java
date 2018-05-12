package de.htw.ai.kbe.songsServlet;

public class SongStructure {

	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;

	public SongStructure() {
		super();
	}

	public SongStructure(Integer id, String title, String artist, String album, Integer released) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.released = released;
	}

	Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public Integer getReleased() {
		return released;
	}

}
