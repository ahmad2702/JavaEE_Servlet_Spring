package de.htw.ai.kbe.songsServlet;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ConverterTest { 
	
	@Test
    public void xmltoSongsTest() throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
    		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + 
				"<songs>\n" + 
				"	<song>\n" + 
				"    		<title>Noch ein Titel1</title>\n" + 
				"      	  	<artist>Noch ein Kuenstler</artist>\n" + 
				"       	 	<album>Noch ein Album</album>\n" + 
				"        	<released>2018</released>\n" + 
				"	</song>\n" + 
				"	<song>\n" + 
				"    		<title>Noch ein Titel2</title>\n" + 
				"      	  	<artist>Noch ein Kuenstler</artist>\n" + 
				"       	 	<album>Noch ein Album</album>\n" + 
				"        	<released>2018</released>\n" + 
				"	</song>\n" + 
				"	<song>\n" + 
				"    		<title>Noch ein Titel3</title>\n" + 
				"      	  	<artist>Noch ein Kuenstler</artist>\n" + 
				"       	 	<album>Noch ein Album</album>\n" + 
				"        	<released>2018</released>\n" + 
				"	</song>\n" + 
				"</songs>";
		
		String erwartet = "Songs [ [id=null, title=Noch ein Titel1, artist=Noch ein Kuenstler, album=Noch ein Album, released=2018] ,  [id=null, title=Noch ein Titel2, artist=Noch ein Kuenstler, album=Noch ein Album, released=2018] ,  [id=null, title=Noch ein Titel3, artist=Noch ein Kuenstler, album=Noch ein Album, released=2018] ]";

		Songs songs = Converter.getSongsFromXmlString(xml);
		String out = "" + songs;
		
		assertEquals(erwartet, out);
    }
	
	@Test
    public void songsToXmlTest() throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
    		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + 
				"<songs>\n" + 
				"	<song>\n" + 
				"    		<title>Noch ein Titel1</title>\n" + 
				"      	  	<artist>Noch ein Kuenstler</artist>\n" + 
				"       	 	<album>Noch ein Album</album>\n" + 
				"        	<released>2018</released>\n" + 
				"	</song>\n" + 
				"	<song>\n" + 
				"    		<title>Noch ein Titel2</title>\n" + 
				"      	  	<artist>Noch ein Kuenstler</artist>\n" + 
				"       	 	<album>Noch ein Album</album>\n" + 
				"        	<released>2018</released>\n" + 
				"	</song>\n" + 
				"	<song>\n" + 
				"    		<title>Noch ein Titel3</title>\n" + 
				"      	  	<artist>Noch ein Kuenstler</artist>\n" + 
				"       	 	<album>Noch ein Album</album>\n" + 
				"        	<released>2018</released>\n" + 
				"	</song>\n" + 
				"</songs>";

		String erwartet = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><songs><song><title>Noch ein Titel1</title><artist>Noch ein Kuenstler</artist><album>Noch ein Album</album><released>2018</released></song><song><title>Noch ein Titel2</title><artist>Noch ein Kuenstler</artist><album>Noch ein Album</album><released>2018</released></song><song><title>Noch ein Titel3</title><artist>Noch ein Kuenstler</artist><album>Noch ein Album</album><released>2018</released></song></songs>";
		
		Songs songs = Converter.getSongsFromXmlString(xml);
		String out = Converter.getXmlFromSongs(songs);
		
		assertEquals(erwartet, out);
    }
	
	
}
