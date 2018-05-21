package de.htw.ai.kbe.songsServlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import myTest.Parser;

import org.junit.Test;

public class MyTest {
	
	@Test
    public void allTest() throws JsonGenerationException, JsonMappingException, IOException {
    		
		Parser parser = new Parser();
		String out = parser.getJsonFormat("songs.json");
		System.out.println("\n Test-All: \n" + out);
    }
	
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
		
		Parser parser = new Parser();
		Songs songs = parser.getSongsFromXmlString(xml);
		System.out.println("\n Test-getSongs: \n" + songs);
		
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
		
		String json = "{\n" + 
				"    \"1\": {\n" + 
				"        \"title\": \"Can’t Stop the Feeling\",\n" + 
				"        \"artist\": \"Justin Timberlake\",\n" + 
				"        \"album\": \"Trolls\",\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"2\": {\n" + 
				"        \"title\": \"Mom\",\n" + 
				"        \"artist\": \"Meghan Trainor, Kelli Trainor\",\n" + 
				"        \"album\": \"Thank You\",\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"3\": {\n" + 
				"        \"title\": \"Team\",\n" + 
				"        \"artist\": \"Iggy Azalea\",\n" + 
				"        \"album\": null,\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"4\": {\n" + 
				"        \"title\": \"Ghostbusters (I'm not a fraid)\",\n" + 
				"        \"artist\": \"Fall Out Boy, Missy Elliott\",\n" + 
				"        \"album\": \"Ghostbusters\",\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"5\": {\n" + 
				"        \"title\": \"Bad Things\",\n" + 
				"        \"artist\": \"Camila Cabello, Machine Gun Kelly\",\n" + 
				"        \"album\": \"Bloom\",\n" + 
				"        \"released\": 2017\n" + 
				"    },\n" + 
				"    \"6\": {\n" + 
				"        \"title\": \"I Took a Pill in Ibiza\",\n" + 
				"        \"artist\": \"Mike Posner\",\n" + 
				"        \"album\": \"At Night, Alone.\",\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"7\": {\n" + 
				"        \"title\": \"i hate u, i love u\",\n" + 
				"        \"artist\": \"Gnash\",\n" + 
				"        \"album\": \"Top Hits 2017\",\n" + 
				"        \"released\": 2017\n" + 
				"    },\n" + 
				"    \"8\": {\n" + 
				"        \"title\": \"No\",\n" + 
				"        \"artist\": \"Meghan Trainor\",\n" + 
				"        \"album\": \"Thank You\",\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"9\": {\n" + 
				"        \"title\": \"Private Show\",\n" + 
				"        \"artist\": \"Britney Spears\",\n" + 
				"        \"album\": \"Glory\",\n" + 
				"        \"released\": 2016\n" + 
				"    },\n" + 
				"    \"10\": {\n" + 
				"        \"title\": \"7 Years\",\n" + 
				"        \"artist\": \"Lukas Graham\",\n" + 
				"        \"album\": \"Lukas Graham (Blue Album)\",\n" + 
				"        \"released\": 2015\n" + 
				"    },\n" + 
				"    \"11\": {\n" + 
				"        \"title\": \"a new title\",\n" + 
				"        \"artist\": \"YOU\",\n" + 
				"        \"album\": \"First Album\",\n" + 
				"        \"released\": 2017\n" + 
				"    }\n" + 
				"}";
		
		Parser parser = new Parser();
		Songs songs = parser.getSongsFromXmlString(xml);
		
		String out = parser.getXmlFromSongs(songs);
		System.out.println("\n Test-getXml: \n" + out);
    }
	
	
}
