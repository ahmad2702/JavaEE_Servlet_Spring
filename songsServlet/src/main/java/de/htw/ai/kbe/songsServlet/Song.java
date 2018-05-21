package de.htw.ai.kbe.songsServlet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "song" )
public class Song
{
	private Integer id;
	
    private String released;

    private String title;

    private String album;

    private String artist;
    
    Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id = id;
	}
    
    public String getReleased ()
    {
        return released;
    }

    public void setReleased (String released)
    {
        this.released = released;
    }

    public String getTitle ()
    {
        return title;
    }
    
    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getAlbum ()
    {
        return album;
    }
    
    public void setAlbum (String album)
    {
        this.album = album;
    }
    
    public String getArtist ()
    {
        return artist;
    }
    
    public void setArtist (String artist)
    {
        this.artist = artist;
    }

    @Override
    public String toString()
    {
        return "{\n" + 
        		//"	\"id\": "+ id + ",\n" + 
        		"	\"title\": \""+ title + "\",\n" + 
        		"	\"artist\": \""+ artist + "\",\n" + 
        		"	\"album\": \""+ album + "\",\n" + 
        		"	\"released\": "+ released + "\n" + 
        		"}";
    }
}
