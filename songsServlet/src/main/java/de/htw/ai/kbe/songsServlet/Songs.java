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

	public List<Song> getSongs() {
		return songs;
	}

	@Override
    public String toString()
    {
    		String output = "";
    		
    		for (int i = 0; i < songs.size(); i++) {
    			output += songs.get(i) + "\n";
    		}
        return output;
    }
}
