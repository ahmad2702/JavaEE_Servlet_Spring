package de.htw.ai.kbe.songsServlet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "songs")
public class Songs{
	
	@XmlElement(name = "song", type = Song.class)
	private List<Song> songs = new ArrayList<Song>();

    public void setSong (Song song)
    {
        songs.add(song); 
    }

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Song> getSongs() {
		return songs;
	}

	@Override
	public String toString() {
		return "Songs " + songs;
	}

	
}
