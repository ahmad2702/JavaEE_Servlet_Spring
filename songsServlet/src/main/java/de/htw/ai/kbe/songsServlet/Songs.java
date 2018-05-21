package de.htw.ai.kbe.songsServlet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Songs
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "songs")
public class Songs{
	
	@XmlElement(name = "song", type = Song.class)
	private List<Song> songs = new ArrayList<Song>();

    /**
     * @param song
     */
    public void setSong (Song song)
    {
        songs.add(song); 
    }

	/**
	 * @param songs
	 */
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	/**
	 * @return songs
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Songs " + songs;
	}

	
}
